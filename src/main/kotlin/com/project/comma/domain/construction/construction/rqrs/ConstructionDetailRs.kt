package com.project.comma.domain.construction.construction.rqrs

import com.project.comma.domain.user.users.Users
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalTime

data class ConstructionDetailRs(

    @Schema(description = "시공 Sn")
    val sn: Long? = null,

    @Schema(description = "시공하는 곳")
    val location: String? = null,

    @Schema(description = "총 인원")
    val totalPersonnel: Int? = null,

    @Schema(description = "인당 인건비")
    val laborCost: Int? = null,

    @Schema(description = "자재 및 부자재 사용량 (미터 기준)")
    val quantity: Int? = null,

    @Schema(description = "(미터 당) 자재 소비자 가격")
    val materialConsumerPrice: Int? = null,

    @Schema(description = "(미터 당) 자재 시공자 가격")
    val materialContractorPrice: Int? = null,

    @Schema(description = "(미터 당) 부자재 소비자 가격")
    val subMaterialConsumerPrice: Int? = null,

    @Schema(description = "(미터 당) 부자재 시공자 가격")
    val subMaterialContractorPrice: Int? = null,

    @Schema(description = "식사 제공")
    val meal: Boolean? = null,

    @Schema(description = "시공 시작일")
    val startDate: LocalDate? = null,

    @Schema(description = "시공 시작 시간")
    val startTime: LocalTime? = null,
) {
    companion object {
        fun from(dto: ConstructionDto): ConstructionDetailRs {
            return ConstructionDetailRs(
                sn = dto.sn,
                location = dto.location,
                totalPersonnel = dto.totalPersonnel,
                laborCost = (dto.totalLaborCost!! / dto.totalPersonnel!!),
                quantity = dto.quantity,
                materialConsumerPrice = dto.materialConsumerPrice,
                materialContractorPrice = dto.materialContractorPrice,
                subMaterialConsumerPrice = dto.subMaterialConsumerPrice,
                subMaterialContractorPrice = dto.subMaterialContractorPrice,
                meal = dto.totalMealCost != 0,
                startDate = dto.startDate,
                startTime = dto.startTime
            )
        }
    }
}
