package com.project.comma.domain.construction.material


import com.project.comma.domain.construction.construction.Construction
import com.project.comma.domain.construction.construction.rqrs.ConstructionRq
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MaterialService(
    private val materialRepository: MaterialRepository,
) {

    @Transactional
    fun saveMaterial(constructionEntity: Construction, rq: ConstructionRq) {
        val materialEntity: Material = materialRepository.findByConstruction(constructionEntity)
            ?.updateMaterial(rq) ?: Material.createMaterial(constructionEntity, rq)

        materialRepository.save(materialEntity)
    }



}