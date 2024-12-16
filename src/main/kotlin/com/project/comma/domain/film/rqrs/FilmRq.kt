package com.project.comma.domain.film.rqrs

import io.swagger.v3.oas.annotations.media.Schema

data class FilmRq(

    @Schema(description = "브랜드 SN")
    val brandSn: Long? = null,

    @Schema(description = "필름지 Code")
    val code: String? = null,

    @Schema(description = "필름지 이름")
    val name: String? = null,

    @Schema(description = "필름지 색상")
    val color: String? = null,

    @Schema(description = "필름지 이미지 URL")
    val imageUrl: String? = null,

    @Schema(description = "필름지 소비자 가격")
    val consumerPrice: Int? = null,

    @Schema(description = "필름지 내 가격")
    val myPrice: Int? = null,
)
