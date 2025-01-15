package com.project.comma.domain.brand

import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import com.project.comma.common.response.BaseResponse
import com.project.comma.domain.brand.rqrs.BrandRq
import com.project.comma.domain.film.rqrs.FilmRq
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/brand")
@Tag(name = "Brand", description = "브랜드 관련 API")
class BrandController(
    private val brandService: BrandService
) {

  @GetMapping
  @Operation(summary = "브랜드 전체 조회", description = "브랜드의 전체 조회 합니다.")
  fun searchBrand(): BaseResponse<List<Brand>> {
    return BaseResponse(data = brandService.searchBrand())
  }

  @PostMapping
  @Operation(summary = "브랜드 정보 저장", description = "브랜드의 정보를 저장 합니다.")
  fun saveBrand(@RequestBody rq: BrandRq): BaseResponse<Unit> {
    if (rq.sn != null || rq.name.isNullOrEmpty() || rq.url == null) throw CommonException(CommonExceptionCode.BAD_REQUEST)
    val resultMsg: String = brandService.saveBrand(rq)
    return BaseResponse(message = resultMsg)
  }

  @PatchMapping("/{brandSn}")
  @Operation(summary = "브랜드 정보 수정", description = "브랜드의 정보를 수정 합니다.")
  fun updateBrand(@PathVariable brandSn: Long, @RequestBody rq: BrandRq): BaseResponse<Unit> {
    if (rq.name == null && rq.url == null) throw CommonException(CommonExceptionCode.BAD_REQUEST)
    rq.sn = brandSn
    val resultMsg: String = brandService.saveBrand(rq)
    return BaseResponse(message = resultMsg)
  }

  @DeleteMapping("/{brandSn}")
  @Operation(summary = "브랜드 정보 삭제", description = "브랜드의 정보를 삭제 합니다.")
  fun deleteBrand(@PathVariable brandSn: Long): BaseResponse<Unit> {
    val resultMsg: String = brandService.deleteBrand(brandSn)
    return BaseResponse(message = resultMsg)
  }
}