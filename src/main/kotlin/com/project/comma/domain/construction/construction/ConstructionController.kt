package com.project.comma.domain.construction.construction

import com.project.comma.common.authority.JwtTokenProvider
import com.project.comma.common.authority.TokenExtraction
import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import com.project.comma.common.response.BaseResponse
import com.project.comma.domain.construction.construction.rqrs.*
import com.project.comma.domain.user.users.Users
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.time.DateTimeException
import java.time.YearMonth

@RestController
@RequestMapping("/api/construction")
@Tag(name = "Construction", description = "시공 관련 API")
class ConstructionController(
  private val constructionService: ConstructionService,
  private val jwtTokenProvider: JwtTokenProvider
) {

  @PostMapping
  @Operation(summary = "시공 내용 저장", description = "시공의 내용을 저장 합니다.")
  fun saveConstruction(@RequestBody rq: ConstructionRq,
                       @RequestHeader("Authorization") auth: String): BaseResponse<Unit> {

    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    rq.user = Users.from(tokenExtraction.userSn)

    val resultMsg: String = constructionService.saveConstruction(rq)
    return BaseResponse(message = resultMsg)
  }

  @GetMapping("/{constructionSn}")
  @Operation(summary = "시공 내용 상세 조회", description = "시공의 상세 내용을 조회한다.")
  fun searchDetailConstruction(@PathVariable constructionSn: Long,
                               @RequestHeader("Authorization") auth: String): BaseResponse<ConstructionDetailRs> {
    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    return BaseResponse(data = constructionService.searchDetailConstruction(constructionSn, tokenExtraction.userSn))
  }

  @GetMapping
  @Operation(summary = "한달 간 시공 내용 조회", description = "한달 간 시공 내용들을 조회한다.")
  fun searchConstruction(@RequestParam yearMonth: YearMonth,
                         @RequestParam location: String?,
                         @RequestHeader("Authorization") auth: String): BaseResponse<List<ConstructionRs>> {
    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    return BaseResponse(data = constructionService.searchConstruction(yearMonth, location, tokenExtraction.userSn))
  }

  @GetMapping("/receipt/{constructionSn}")
  @Operation(summary = "시공 내용 영수증", description = "시공의 내용을 영수증화 시켜 보여준다.")
  fun searchConstructionReceipt(@PathVariable constructionSn: Long,
                                @RequestHeader("Authorization") auth: String): BaseResponse<ConstructionReceiptRs> {
    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    return BaseResponse(data = constructionService.searchConstructionReceipt(constructionSn, tokenExtraction.userSn))
  }

  // TODO 정산 내역 조회
  // 한달 간으로 조회
  // -> sn, 시공 위치, 매출, 비용, 순이익 --> 리스트
  // -> 총 매출, 총 비용, 최종 수익
  @GetMapping("/adjustment")
  @Operation(summary = "한달 간 시공 정산", description = "한달 간 시공의 정산 내용들을 보여준다.")
  fun searchConstructionAdjustment(@RequestParam yearMonth: YearMonth,
                                   @RequestHeader("Authorization") auth: String): BaseResponse<ConstructionAdjustmentRs> {
    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    return BaseResponse(data = constructionService.searchConstructionAdjustment(yearMonth, tokenExtraction.userSn))
  }

  @PutMapping("/{constructionSn}")
  @Operation(summary = "시공 내용 수정", description = "시공의 내용을 수정 합니다.")
  fun saveConstruction(@PathVariable constructionSn: Long,
                       @RequestBody rq: ConstructionRq,
                       @RequestHeader("Authorization") auth: String): BaseResponse<Unit> {

    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    rq.user = Users.from(tokenExtraction.userSn)
    rq.sn = constructionSn

    val resultMsg: String = constructionService.saveConstruction(rq)
    return BaseResponse(message = resultMsg)
  }

  @DeleteMapping("/{constructionSn}")
  @Operation(summary = "시공 내용 삭제", description = "시공의 내용을 삭제 합니다.")
  fun deleteConstruction(@PathVariable constructionSn: Long,
                         @RequestHeader("Authorization") auth: String): BaseResponse<Unit> {

    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    val resultMsg: String = constructionService.deleteConstruction(constructionSn, tokenExtraction.userSn)
    return BaseResponse(message = resultMsg)
  }

}