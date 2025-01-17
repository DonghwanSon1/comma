package com.project.comma.domain.construction.construction

import com.project.comma.domain.brand.QBrand
import com.project.comma.domain.construction.construction.rqrs.ConstructionReceiptRs
import com.project.comma.domain.construction.construction.rqrs.ConstructionRqDto
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
                    user.name.`as`("userName"),
                    user.phone.`as`("userPhone")
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

}