package com.project.comma.domain.film

import com.project.comma.domain.brand.QBrand
import com.project.comma.domain.film.rqrs.FilmRs
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class FilmCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : FilmCustomRepository {
    private val brand: QBrand = QBrand.brand
    private val film: QFilm = QFilm.film

    override fun searchFilm(brandSn: Long?, code: String?, pageable: Pageable): Page<FilmRs> {
        val builder = BooleanBuilder()
        brandSn?.let { builder.and(film.brand.sn.eq(it)) }
        if (!code.isNullOrEmpty()) builder.and(film.code.like("%$code%"))

        val results = queryFactory
            .select(
                Projections.fields(
                    FilmRs::class.java,
                    film.sn.`as`("filmSn"),
                    brand.name.`as`("brandName"),
                    brand.url.`as`("brandUrl"),
                    film.code.`as`("filmCode"),
                    film.name.`as`("filmName"),
                    film.color.`as`("filmColor"),
                    film.imageUrl.`as`("filmImageUrl"),
                    film.consumerPrice.`as`("filmConsumerPrice"),
                    film.myPrice.`as`("filmMyPrice")
                )
            )
            .from(film)
            .join(brand).on(film.brand.eq(brand))
            .where(builder) // 조건 추가
            .orderBy(film.sn.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val count = queryFactory
            .selectFrom(film)
            .join(brand).on(film.brand.eq(brand))
            .where(builder)
            .fetchCount()

        return PageImpl(results, pageable, count)


    }
}