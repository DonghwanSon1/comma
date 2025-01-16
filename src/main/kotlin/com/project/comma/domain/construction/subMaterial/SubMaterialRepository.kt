package com.project.comma.domain.construction.subMaterial

import com.project.comma.domain.construction.construction.Construction
import org.springframework.data.jpa.repository.JpaRepository

interface SubMaterialRepository: JpaRepository<SubMaterial, Long>, SubMaterialCustomRepository {

  fun findByConstruction(constructionEntity: Construction): SubMaterial?

}