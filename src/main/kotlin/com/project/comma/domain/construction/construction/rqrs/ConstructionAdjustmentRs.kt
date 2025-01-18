package com.project.comma.domain.construction.construction.rqrs

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalTime

data class ConstructionAdjustmentRs(

    @Schema(description = "총 매출")
    val totalSales: Int? = null,

    @Schema(description = "총 비용")
    val totalCost: Int? = null,

    @Schema(description = "최종 순이익")
    val finalNetProfit: Int? = null,

    @Schema(description = "Simple 정산 내역 리스트")
    val adjustmentList: List<ConstructionAdjustmentSimpleRs>? = null,

)
