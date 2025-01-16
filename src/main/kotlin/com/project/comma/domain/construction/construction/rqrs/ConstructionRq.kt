package com.project.comma.domain.construction.construction.rqrs

import com.project.comma.domain.user.users.Users
import io.swagger.v3.oas.annotations.media.Schema

data class ConstructionRq(

    @Schema(description = "시공 Sn")
    var sn: Long? = null,

    @Schema(description = "유저")
    var user: Users? = null,

    @Schema(description = "시공하는 곳")
    val location: String,

    @Schema(description = "총 인원")
    val totalPersonnel: Int,

    @Schema(description = "총 인건비")
    val laborCost: Int,

    @Schema(description = "자재 및 부자재 사용량 (미터 기준)")
    val quantity: Int,

    @Schema(description = "(미터 당) 자재 소비자 가격")
    val materialConsumerPrice: Int,

    @Schema(description = "(미터 당) 자재 시공자 가격")
    val materialContractorPrice: Int,

    @Schema(description = "(미터 당) 부자재 소비자 가격")
    val subMaterialConsumerPrice: Int,

    @Schema(description = "(미터 당) 부자재 시공자 가격")
    val subMaterialContractorPrice: Int,

    @Schema(description = "시공 시작일")
    val startDate: String,

    @Schema(description = "시공 시작 시간")
    val startTime: String,

)

//총 인건비
//540,000
//
//소비자 가격
//110,000
//
//시공자 가격
//20,000