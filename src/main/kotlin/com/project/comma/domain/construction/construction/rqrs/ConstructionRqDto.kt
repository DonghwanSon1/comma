package com.project.comma.domain.construction.construction.rqrs

import com.project.comma.domain.construction.construction.ConstructionService
import com.project.comma.domain.user.users.Users
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalTime

data class ConstructionRqDto(

    @Schema(description = "유저")
    var user: Users,

    @Schema(description = "시공하는 곳")
    val location: String,

    @Schema(description = "총 인원")
    val totalPersonnel: Int,

    @Schema(description = "총 인건비")
    val totalLaborCost: Int,

    @Schema(description = "자재 및 부자재의 총 소비자 가격")
    val totalConsumerCost: Int,

    @Schema(description = "자재 및 부자재의 총 시공자 가격")
    val totalContractorCost: Int,

    @Schema(description = "총 식비")
    val totalMealCost: Int,

    @Schema(description = "시공 시작일")
    val startDate: LocalDate,

    @Schema(description = "시공 시작시간")
    val startTime: LocalTime,

) {
    companion object {
        fun createConstructionDto(rq: ConstructionRq): ConstructionRqDto {
            return ConstructionRqDto(
                user = rq.user!!,
                location = rq.location,
                totalPersonnel = rq.totalPersonnel,
                // 인원수 * 인건비, 총 인건비
                totalLaborCost = rq.totalPersonnel * rq.laborCost,
                // (사용량 * 소비자 자재값) + (사용량 * 소비자 부자재 값), 총 자재 및 부자재 소비자 가격
                totalConsumerCost = (rq.quantity * rq.materialConsumerPrice) + (rq.quantity * rq.subMaterialConsumerPrice),
                // (사용량 * 시공자 자재값) + (사용량 * 시공자 부자재 값), 총 자재 및 부자재 시공자 가격
                totalContractorCost = (rq.quantity * rq.materialContractorPrice) + (rq.quantity * rq.subMaterialContractorPrice),
                // 식대 값 (인당 10,000원)
                totalMealCost = if (rq.meal) rq.totalPersonnel * ConstructionService.MEAL_COST_PER_PERSON else 0,
                startDate = rq.startDate,
                startTime = rq.startTime
            )
        }
    }
}
