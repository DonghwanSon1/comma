package com.project.comma.domain.film

import com.project.comma.domain.film.rqrs.FilmRs
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface FilmCustomRepository {

  fun searchFilm(brandSn: Long?, code: String?, pageable: Pageable): Page<FilmRs>

}