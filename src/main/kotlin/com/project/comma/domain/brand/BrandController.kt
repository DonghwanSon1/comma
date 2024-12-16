package com.project.comma.domain.brand

import com.project.comma.common.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/brand")
@Tag(name = "Brand", description = "브랜드 관련 API")
class BrandController(
    private val brandService: BrandService
) {

  @GetMapping
  @Operation(summary = "브랜드 전체 조회", description = "브랜드의 전체 조회 합니다.")
  fun searchBrand(): BaseResponse<List<Brand>> {
    return BaseResponse(data = brandService.searchBrand())
  }
}