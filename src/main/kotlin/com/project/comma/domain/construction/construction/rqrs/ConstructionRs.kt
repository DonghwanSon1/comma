package com.project.comma.domain.construction.construction.rqrs

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalTime

data class ConstructionRs(

    @Schema(description = "시공 Sn")
    val sn: Long? = null,

    @Schema(description = "시공 시작일")
    val startDate: LocalDate? = null,

    @Schema(description = "시공하는 곳")
    val location: String? = null,

    @Schema(description = "총 소비자 가격")
    val allConsumerCost: Int? = null,

){
}
