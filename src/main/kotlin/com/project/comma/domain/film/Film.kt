package com.project.comma.domain.film

import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import com.project.comma.domain.brand.Brand
import com.project.comma.domain.film.rqrs.FilmRq
import javax.persistence.*

@Entity
@Table(name = "film")
class Film(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    val sn: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_sn", foreignKey = ForeignKey(name = "FK_FILM_BRAND_SN"))
    val brand: Brand? = null,

    @Column(name = "code", nullable = false)
    val code: String? = null,

    @Column(name = "name")
    val name: String? = null,

    @Column(name = "color")
    val color: String? = null,

    @Column(name = "image_url")
    val imageUrl: String? = null,

    @Column(name = "consumer_price", nullable = false)
    val consumerPrice: Int? = null,

    @Column(name = "my_price")
    val myPrice: Int? = null,
) {
    companion object {
        fun createFilm(rq: FilmRq): Film {
            return Film(
                brand = Brand(sn = rq.brandSn),
                code = rq.code,
                name = rq.name,
                color = rq.color,
                imageUrl = rq.imageUrl,
                consumerPrice = rq.consumerPrice ?: 0,
                myPrice = rq.myPrice ?: 0
            )
        }
    }

    fun updateFilm(rq: FilmRq): Film {
        return Film(
            sn = this.sn,
            brand = if (rq.brandSn != null) Brand(sn = rq.brandSn) else this.brand,
            code = rq.code ?: this.code,
            name = rq.name ?: this.name,
            color = rq.color ?: this.color,
            imageUrl = rq.imageUrl ?: this.imageUrl,
            consumerPrice = rq.consumerPrice ?: this.consumerPrice,
            myPrice = rq.myPrice ?: this.myPrice
        )
    }
}