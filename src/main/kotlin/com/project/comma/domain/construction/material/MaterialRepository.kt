package com.project.comma.domain.construction.material

import com.project.comma.domain.construction.construction.Construction
import org.springframework.data.jpa.repository.JpaRepository

interface MaterialRepository: JpaRepository<Material, Long>, MaterialCustomRepository {

  fun findByConstruction(constructionEntity: Construction): Material?

}