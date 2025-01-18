package com.project.comma.domain.construction.construction


import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import com.project.comma.domain.construction.construction.rqrs.*
import com.project.comma.domain.construction.material.MaterialService
import com.project.comma.domain.construction.subMaterial.SubMaterialService
import com.project.comma.domain.user.users.Users
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.YearMonth

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

  /**
   * 시공 내용들 조회
   */
  fun searchConstruction(yearMonth: YearMonth, location: String?, userSn: Long): List<ConstructionRs>? {
    return constructionRepository.searchConstruction(yearMonth, location, userSn)
  }

  /**
   * 시공 상세 조회
   */
  fun searchDetailConstruction(constructionSn: Long, userSn: Long): ConstructionDetailRs {
    val dto: ConstructionDto = constructionRepository.searchRqConstruction(constructionSn, userSn)
      ?: throw CommonException(CommonExceptionCode.DOES_NOT_EXIST_CONSTRUCTION)

    return ConstructionDetailRs.from(dto)
  }

  /**
   * 시공 영수증 조회
   */
  fun searchConstructionReceipt(constructionSn: Long, userSn: Long): ConstructionReceiptRs {
    return constructionRepository.searchConstructionReceipt(constructionSn, userSn)
      ?: throw CommonException(CommonExceptionCode.DOES_NOT_EXIST_CONSTRUCTION)
  }

  /**
   * 시공 정산 내역
   */
  fun searchConstructionAdjustment(yearMonth: YearMonth, userSn: Long): ConstructionAdjustmentRs {
    val dto: List<ConstructionDto>? = constructionRepository.searchConstructionAdjustment(yearMonth, userSn)

    var totalSales: Int = 0
    var totalCost: Int = 0
    var finalNetProfit: Int = 0


    val simpleRsList: List<ConstructionAdjustmentSimpleRs>? = dto?.map { dto ->
      val sales = dto.totalLaborCost!! + dto.totalConsumerCost!! + dto.totalMealCost!!
      val cost = dto.totalContractorCost!! + dto.totalMealCost!!
      val netProfit = sales - cost

      totalSales += sales
      totalCost += cost
      finalNetProfit += netProfit

      ConstructionAdjustmentSimpleRs(
        sn = dto.sn,
        location = dto.location,
        sales = sales,
        cost = cost,
        netProfit = netProfit
      )
    }

    return ConstructionAdjustmentRs(
      totalSales = totalSales,
      totalCost = totalCost,
      finalNetProfit = finalNetProfit,
      adjustmentList = simpleRsList
    )
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

  @Transactional
  fun deleteConstruction(constructionSn: Long, userSn: Long): String {
    // 부모 테이블인 Construction 만 삭제해도 하위 자식테이블들 삭제 된다 => ( ON DELETE CASCADE 설정 )
    val entity: Construction = constructionRepository.findBySnAndUser(constructionSn, Users.from(userSn))
      ?: throw CommonException(CommonExceptionCode.DOES_NOT_EXIST_CONSTRUCTION)

    constructionRepository.delete(entity)
    return "성공적으로 삭제했습니다."
  }




}