package com.project.comma.domain.construction.construction.rqrs

import com.project.comma.domain.construction.construction.ConstructionService
import com.project.comma.domain.user.users.Users
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalTime

data class ConstructionDto(

    // Construction Table
    @Schema(description = "시공 Sn")
    var sn: Long? = null,

    @Schema(description = "시공하는 곳")
    val location: String? = null,

    @Schema(description = "총 인원")
    val totalPersonnel: Int? = null,

    @Schema(description = "총 인건비")
    val totalLaborCost: Int? = null,

    @Schema(description = "총 자재 및 부자재 소비자 가격")
    val totalConsumerCost: Int? = null,

    @Schema(description = "총 자재 및 부자재 시공자 가격")
    val totalConstructorCost: Int? = null,

    @Schema(description = "식사 총 가격")
    val totalMealCost: Int? = null,

    @Schema(description = "시공 시작일")
    val startDate: LocalDate? = null,

    @Schema(description = "시공 시작 시간")
    val startTime: LocalTime? = null,


    // Material Table
    @Schema(description = "자재 및 부자재 사용량 (미터 기준)")
    val quantity: Int? = null,

    @Schema(description = "(미터 당) 자재 소비자 가격")
    val materialConsumerPrice: Int? = null,

    @Schema(description = "(미터 당) 자재 시공자 가격")
    val materialConstructorPrice: Int? = null,


    // SubMaterial Table
    @Schema(description = "(미터 당) 부자재 소비자 가격")
    val subMaterialConsumerPrice: Int? = null,

    @Schema(description = "(미터 당) 부자재 시공자 가격")
    val subMaterialConstructorPrice: Int? = null,


    // User Table
    @Schema(description = "시공 대표자")
    val userName: String? = null,

    @Schema(description = "시공 대표자 번호")
    val userPhone: String? = null,

) {
}
