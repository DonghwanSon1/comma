package com.project.comma.common.exception


class CommonException(val exceptionCode: CommonExceptionCode) : RuntimeException(exceptionCode.message) {

}