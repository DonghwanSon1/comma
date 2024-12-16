package com.project.comma.domain.brand

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
}