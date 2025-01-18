package com.project.comma.domain.construction.construction

import com.project.comma.common.authority.JwtTokenProvider
import com.project.comma.common.authority.TokenExtraction
import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import com.project.comma.common.response.BaseResponse
import com.project.comma.domain.construction.construction.rqrs.ConstructionReceiptRs
import com.project.comma.domain.construction.construction.rqrs.ConstructionRq
import com.project.comma.domain.construction.construction.rqrs.ConstructionRs
import com.project.comma.domain.user.users.Users
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
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

  // TODO 영수증 전체 조회 (년도의 월별), 수정 할 수 있게 디테일 정보 조회
  // -> 년/달 별로 조회하며, 장소로 검색할 수 있음. 년/달은 필수, 장소는 선택
  // -> Rs는 sn, 시공일, 장소, 총 소비자 가격
  @GetMapping
  @Operation(summary = "시공 내용 조회", description = "년/달 별로 시공의 내용들을 조회한다.")
  fun searchConstruction(@RequestParam yearMonth: String,
                         @RequestParam location: String?,
                         @RequestHeader("Authorization") auth: String): BaseResponse<List<ConstructionRs>> {
    if (yearMonth.isNullOrEmpty()) throw CommonException(CommonExceptionCode.BAD_REQUEST)
    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    return BaseResponse(data = constructionService.searchConstruction(YearMonth.parse(yearMonth), location, tokenExtraction.userSn))
  }

  @GetMapping("/receipt/{constructionSn}")
  @Operation(summary = "시공 내용 영수증", description = "시공의 내용을 영수증화 시켜 보여준다.")
  fun searchConstructionReceipt(@PathVariable constructionSn: Long,
                                @RequestHeader("Authorization") auth: String): BaseResponse<ConstructionReceiptRs> {
    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    return BaseResponse(data = constructionService.searchConstructionReceipt(constructionSn, tokenExtraction.userSn))
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

}