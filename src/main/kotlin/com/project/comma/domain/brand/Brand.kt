package com.project.comma.domain.brand

import com.project.comma.domain.brand.rqrs.BrandRq
import javax.persistence.*

@Entity
@Table(name = "brand")
class Brand(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    val sn: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String? = null,

    @Column(name = "url", nullable = false)
    val url: String? = null,
) {
    companion object {
        fun createBrand(rq: BrandRq): Brand {
            return Brand(
                name = rq.name,
                url = rq.url
            )
        }
    }

    fun updateBrand(rq: BrandRq): Brand {
        return Brand(
            sn = this.sn,
            name = rq.name ?: this.name,
            url = rq.url ?: this.url
        )
    }
}