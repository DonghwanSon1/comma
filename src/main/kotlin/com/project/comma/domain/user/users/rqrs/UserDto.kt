package com.project.comma.domain.user.users.rqrs

import io.swagger.v3.oas.annotations.media.Schema

data class UserDto(

    @Schema(description = "유저 SN")
    val sn: Long? = null,

    @Schema(description = "유저 ID")
    val email: String? = null,

    @Schema(description = "유저 PW")
    val password: String? = null,

    @Schema(description = "유저 이름")
    val name: String? = null,

    @Schema(description = "유저 핸드폰")
    val phone: String? = null,

    @Schema(description = "유저 연봉(일당)")
    val salary: Long? = null,

    @Schema(description = "유저 역할")
    val role: String? = null
)
