package com.project.comma.domain.film

import org.springframework.data.jpa.repository.JpaRepository

interface FilmRepository: JpaRepository<Film, Long>, FilmCustomRepository {

}