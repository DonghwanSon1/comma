package com.project.comma.domain.construction.subMaterial


import com.project.comma.domain.construction.construction.Construction
import com.project.comma.domain.construction.construction.rqrs.ConstructionRq
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SubMaterialService(
    private val subMaterialRepository: SubMaterialRepository,
) {

    @Transactional
    fun saveSubMaterial(constructionEntity: Construction, rq: ConstructionRq) {
        val subMaterialEntity: SubMaterial = subMaterialRepository.findByConstruction(constructionEntity)
            ?.updateSubMaterial(rq) ?: SubMaterial.createSubMaterial(constructionEntity, rq)

        subMaterialRepository.save(subMaterialEntity)
    }

}