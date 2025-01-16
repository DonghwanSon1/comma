package com.project.comma.domain.construction.construction

import com.project.comma.domain.brand.QBrand
import com.project.comma.domain.construction.material.QMaterial
import com.project.comma.domain.construction.subMaterial.QSubMaterial
import com.project.comma.domain.film.rqrs.FilmRs
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


}