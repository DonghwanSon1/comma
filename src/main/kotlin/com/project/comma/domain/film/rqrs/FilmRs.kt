package com.project.comma.domain.film.rqrs

import io.swagger.v3.oas.annotations.media.Schema

data class FilmRs(

    @Schema(description = "필름 SN")
    val filmSn: Long? = null,

    @Schema(description = "브랜드 이름")
    val brandName: String? = null,

    @Schema(description = "브랜드 URL")
    val brandUrl: String? = null,

    @Schema(description = "필름지 Code")
    val filmCode: String? = null,

    @Schema(description = "필름지 이름")
    val filmName: String? = null,

    @Schema(description = "필름지 색상")
    val filmColor: String? = null,

    @Schema(description = "필름지 이미지 URL")
    val filmImageUrl: String? = null,

    @Schema(description = "필름지 소비자 가격")
    val filmConsumerPrice: Int? = null,

    @Schema(description = "필름지 내 가격")
    val filmMyPrice: Int? = null,
)
