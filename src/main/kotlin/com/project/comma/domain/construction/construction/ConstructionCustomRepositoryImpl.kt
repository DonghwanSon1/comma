package com.project.comma.domain.construction.construction

import com.project.comma.domain.brand.QBrand
import com.project.comma.domain.construction.construction.rqrs.ConstructionDto
import com.project.comma.domain.construction.construction.rqrs.ConstructionReceiptRs
import com.project.comma.domain.construction.construction.rqrs.ConstructionRqDto
import com.project.comma.domain.construction.construction.rqrs.ConstructionRs
import com.project.comma.domain.construction.material.QMaterial
import com.project.comma.domain.construction.subMaterial.QSubMaterial
import com.project.comma.domain.film.rqrs.FilmRs
import com.project.comma.domain.user.users.QUsers
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth

@Repository
class ConstructionCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : ConstructionCustomRepository {
    private val construction: QConstruction = QConstruction.construction
    private val material: QMaterial = QMaterial.material
    private val subMaterial: QSubMaterial = QSubMaterial.subMaterial
    private val user: QUsers = QUsers.users

    override fun searchConstructionReceipt(constructionSn: Long, userSn: Long): ConstructionReceiptRs? {

        return queryFactory
            .select(
                Projections.fields(
                    ConstructionReceiptRs::class.java,
                    construction.sn,
                    construction.location,
                    construction.totalPersonnel,
                    construction.totalLaborCost,
                    construction.totalConsumerCost.`as`("totalMatSubCost"),
                    construction.totalMealCost,
                    construction.startDate,
                    construction.startTime,
                    material.quantity,
                    user.name.`as`("constructorName"),
                    user.phone.`as`("constructorPhone")
                )
            )
            .from(construction)
            .join(material).on(construction.eq(material.construction))
            .join(subMaterial).on(construction.eq(subMaterial.construction))
            .join(user).on(construction.user.eq(user))
            .where(construction.user.sn.eq(userSn),
                construction.sn.eq(constructionSn))
            .fetchOne()
    }

    override fun searchConstruction(yearMonth: YearMonth, location: String?, userSn: Long): List<ConstructionRs>? {
        val startDate: LocalDate = yearMonth.atDay(1)
        val endDate: LocalDate = yearMonth.atEndOfMonth()

        val builder = BooleanBuilder()
        if (!location.isNullOrEmpty()) builder.and(construction.location.like("%$location%"))

        return queryFactory
            .select(
                Projections.fields(
                    ConstructionRs::class.java,
                    construction.sn,
                    construction.startDate,
                    construction.location,
                    construction.totalConsumerCost.add(construction.totalLaborCost).`as`("allConsumerCost")
                )
            )
            .from(construction)
            .where(construction.startDate.between(startDate, endDate),
                builder)
            .orderBy(construction.startDate.asc(),
                construction.startTime.asc())
            .fetch()
    }

    override fun searchRqConstruction(constructionSn: Long, userSn: Long): ConstructionDto? {
        return queryFactory
            .select(
                Projections.fields(
                    ConstructionDto::class.java,
                    construction.sn,
                    construction.location,
                    construction.totalPersonnel,
                    construction.totalLaborCost,
                    material.quantity,
                    material.consumerPrice.`as`("materialConsumerPrice"),
                    material.contractorPrice.`as`("materialConstructorPrice"),
                    subMaterial.consumerPrice.`as`("subMaterialConsumerPrice"),
                    subMaterial.contractorPrice.`as`("subMaterialConstructorPrice"),
                    construction.totalMealCost,
                    construction.startDate,
                    construction.startTime
                )
            )
            .from(construction)
            .join(material).on(construction.eq(material.construction))
            .join(subMaterial).on(construction.eq(subMaterial.construction))
            .where(construction.user.sn.eq(userSn),
                construction.sn.eq(constructionSn))
            .fetchOne()
    }
}