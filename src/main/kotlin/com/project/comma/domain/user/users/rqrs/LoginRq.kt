package com.project.comma.domain.user.users.rqrs

import io.swagger.v3.oas.annotations.media.Schema

data class LoginRq(

    @Schema(description = "유저 이메일 id")
    val email: String? = null,

    @Schema(description = "유저 PW")
    val password: String? = null
) {
}
