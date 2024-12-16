package com.project.comma.domain.film


import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import com.project.comma.domain.film.rqrs.FilmRq
import com.project.comma.domain.film.rqrs.FilmRs
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FilmService(
    private val filmRepository: FilmRepository
) {

  @Transactional
  fun saveFilm(rq: List<FilmRq>): String {
    val entityList: List<Film> = rq.filter { it.brandSn != null || it.code != null }.map{ Film.createFilm(it) }
    filmRepository.saveAll(entityList)
    return "정상적으로 저장되었습니다."
  }

  @Transactional
  fun updateFilm(filmSn: Long, rq: FilmRq): String {
    val entity: Film = filmRepository.findById(filmSn).orElseThrow{ CommonException(CommonExceptionCode.DOES_NOT_EXIST_FILM) }
    filmRepository.save(entity.updateFilm(rq))
    return "정상적으로 수정되었습니다."
  }

  fun searchFilm(brandSn: Long?, code: String?, pageable: Pageable): Page<FilmRs> {
    val result: Page<FilmRs> = filmRepository.searchFilm(brandSn, code, pageable)
    return result
  }

}