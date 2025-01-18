package com.project.comma.domain.construction.construction

import com.project.comma.domain.construction.construction.rqrs.ConstructionReceiptRs
import com.project.comma.domain.construction.construction.rqrs.ConstructionRqDto
import com.project.comma.domain.construction.construction.rqrs.ConstructionRs
import com.project.comma.domain.film.rqrs.FilmRs
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.YearMonth


interface ConstructionCustomRepository {
  fun searchConstructionReceipt(constructionSn: Long, userSn: Long): ConstructionReceiptRs?
  fun searchConstruction(yearMonth: YearMonth, location: String?, userSn: Long): List<ConstructionRs>?
}