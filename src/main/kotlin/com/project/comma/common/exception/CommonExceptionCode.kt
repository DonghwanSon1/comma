package com.project.comma.common.exception

import org.springframework.http.HttpStatus

enum class CommonExceptionCode(
        val status: HttpStatus,
        val message: String
) {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "입력값을 확인해주세요."),
    DUPLICATE_ID(HttpStatus.BAD_REQUEST, "중복된 아이디가 있습니다."),
    NOT_EXIST_USER_ID(HttpStatus.BAD_REQUEST, "해당 유저는 존재하지 않습니다."),
    LOGIN_FAIL(HttpStatus.FORBIDDEN, "해당 유저가 존재하지 않거나, 아이디 또는 비밀번호가 틀렸습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    DUPLICATE_DATA_ERROR(HttpStatus.CONFLICT, "중복 데이터 발생했습니다. 입력값을 확인 해주세요."),
    CONSTRAINTS_ERROR(HttpStatus.BAD_REQUEST, "데이터 처리 중 오류가 발생했습니다. 입력값을 확인한 후 다시 시도해주세요."),
    DOES_NOT_EXIST_FILM(HttpStatus.BAD_REQUEST, "해당 필름지는 존재하지 않습니다."),
    DOES_NOT_EXIST_BRAND(HttpStatus.BAD_REQUEST, "해당 브랜드는 존재하지 않습니다."),
    IMAGE_BAD_REQUEST(HttpStatus.BAD_REQUEST, "이미지를 선택해주세요."),
    NO_IMAGE_EXISTS(HttpStatus.BAD_REQUEST, "해당 이미지가 존재하지 않습니다."),
    DOES_NOT_EXIST_CONSTRUCTION(HttpStatus.BAD_REQUEST, "존재하지 않는 시공 정보 입니다."),

}