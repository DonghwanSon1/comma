package com.project.comma.domain.construction.construction.rqrs

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalTime

data class ConstructionAdjustmentSimpleRs(

    @Schema(description = "시공 Sn")
    val sn: Long? = null,

    @Schema(description = "시공하는 곳")
    val location: String? = null,

    @Schema(description = "매출")
    val sales: Int? = null,

    @Schema(description = "비용")
    val cost: Int? = null,

    @Schema(description = "순이익")
    val netProfit: Int? = null,

) {
}