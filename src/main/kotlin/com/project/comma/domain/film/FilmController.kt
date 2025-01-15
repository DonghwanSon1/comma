package com.project.comma.domain.film

import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import com.project.comma.common.response.BaseResponse
import com.project.comma.domain.film.rqrs.FilmRq
import com.project.comma.domain.film.rqrs.FilmRs
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/film")
@Tag(name = "Film", description = "필름지 관련 API")
class FilmController(
    private val filmService: FilmService
) {

  @GetMapping
  @Operation(summary = "필름지 정보 조회", description = "필름지의 정보를 조회 합니다.")
  fun searchFilm(@RequestParam brandSn: Long?,
                 @RequestParam code: String?,
                 @RequestParam(defaultValue = "0") page: Int,
                 @RequestParam(defaultValue = "10") size: Int, ): BaseResponse<Page<FilmRs>> {
    val pageable: Pageable = PageRequest.of(page, size)
    return BaseResponse(data = filmService.searchFilm(brandSn, code, pageable))
  }

  @PostMapping
  @Operation(summary = "필름지 정보 저장", description = "필름지의 정보를 저장 합니다.")
  fun saveFilm(@RequestBody rq: List<FilmRq>): BaseResponse<Unit> {
    if (rq.isNullOrEmpty()) throw CommonException(CommonExceptionCode.BAD_REQUEST)
    val resultMsg: String = filmService.saveFilm(rq)
    return BaseResponse(message = resultMsg)
  }

  @PatchMapping("/{filmSn}")
  @Operation(summary = "필름지 정보 수정", description = "필름지의 정보를 수정 합니다.")
  fun updateFilm(@PathVariable filmSn: Long, @RequestBody rq: FilmRq): BaseResponse<Unit> {
    val resultMsg: String = filmService.updateFilm(filmSn, rq)
    return BaseResponse(message = resultMsg)
  }

  @DeleteMapping("/{filmSn}")
  @Operation(summary = "필름지 정보 삭제", description = "필름지의 정보를 삭제 합니다.")
  fun deleteFilm(@PathVariable filmSn: Long): BaseResponse<Unit> {
    val resultMsg: String = filmService.deleteFilm(filmSn)
    return BaseResponse(message = resultMsg)
  }


}