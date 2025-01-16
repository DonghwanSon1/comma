package com.project.comma.domain.user.users.rqrs

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

data class UserRs(

    @Schema(description = "유저 SN")
    val sn: Long? = null,

    @Schema(description = "유저 ID")
    val email: String? = null,

    @Schema(description = "유저 이름")
    val name: String? = null,

    @Schema(description = "유저 핸드폰")
    val phone: String? = null,

    @Schema(description = "유저 연봉(일당)")
    val salary: Long? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "유저 역할")
    val role: String? = null
)
