package com.project.comma.domain.construction.material

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
class MaterialCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : MaterialCustomRepository {
    private val material: QMaterial = QMaterial.material


}