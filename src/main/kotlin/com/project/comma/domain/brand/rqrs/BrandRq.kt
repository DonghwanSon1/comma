package com.project.comma.domain.brand.rqrs

import io.swagger.v3.oas.annotations.media.Schema

data class BrandRq(

    @Schema(description = "브랜드 sn")
    var sn: Long? = null,

    @Schema(description = "브랜드 이름")
    val name: String? = null,

    @Schema(description = "브랜드 URL")
    val url: String? = null,

)
