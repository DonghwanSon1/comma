package com.project.comma.domain.construction.construction


import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import com.project.comma.domain.construction.construction.rqrs.ConstructionDto
import com.project.comma.domain.construction.construction.rqrs.ConstructionRqDto
import com.project.comma.domain.construction.construction.rqrs.ConstructionReceiptRs
import com.project.comma.domain.construction.construction.rqrs.ConstructionRq
import com.project.comma.domain.construction.material.MaterialService
import com.project.comma.domain.construction.subMaterial.SubMaterialService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ConstructionService(
  private val constructionRepository: ConstructionRepository,
  private val materialService: MaterialService,
  private val subMaterialService: SubMaterialService,
) {
  companion object {
    // 고정값을 상수로 선언 (식대)
    const val MEAL_COST_PER_PERSON = 10000
  }

  @Transactional
  fun saveConstruction(rq: ConstructionRq): String {

    val dto: ConstructionRqDto = ConstructionRqDto.createConstructionDto(rq)

    val constructionEntity: Construction = if (rq.sn == null) {
      Construction.createConstruction(dto)
    } else {
      constructionRepository.findBySnAndUser(rq.sn!!, rq.user!!)
        ?.updateConstruction(dto)
        ?: throw CommonException(CommonExceptionCode.DOES_NOT_EXIST_CONSTRUCTION)
    }

    val saveEntity: Construction = constructionRepository.save(constructionEntity)

    materialService.saveMaterial(saveEntity, rq)
    subMaterialService.saveSubMaterial(saveEntity, rq)

    return if (rq.sn == null ) {
      "시공 정보를 성공적으로 저장되었습니다."
    }
    else {
      "시공 정보를 성공적으로 수정되었습니다."
    }
  }

  fun searchConstructionReceipt(constructionSn: Long, userSn: Long): ConstructionReceiptRs {

    return constructionRepository.searchConstructionReceipt(constructionSn, userSn)
      ?: throw CommonException(CommonExceptionCode.DOES_NOT_EXIST_CONSTRUCTION)
  }


}