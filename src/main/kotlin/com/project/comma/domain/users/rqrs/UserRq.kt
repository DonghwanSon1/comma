package com.project.comma.domain.users.rqrs

import io.swagger.v3.oas.annotations.media.Schema

data class UserRq(

    @Schema(description = "유저 이메일 ID")
    val email: String? = null,

    @Schema(description = "유저 PW")
    val password: String? = null,

    @Schema(description = "유저 이름")
    val name: String? = null,

    @Schema(description = "유저 핸드폰")
    val phone: String? = null,

    @Schema(description = "유저 연봉(일당)")
    val salary: Long? = null
)
