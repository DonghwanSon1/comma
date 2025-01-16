package com.project.comma.domain.construction.subMaterial

import com.project.comma.domain.construction.construction.Construction
import com.project.comma.domain.construction.construction.rqrs.ConstructionRq
import com.project.comma.domain.construction.material.Material
import javax.persistence.*

@Entity
@Table(name = "sub_material")
class SubMaterial(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    val sn: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "construction_sn", nullable = false, foreignKey = ForeignKey(name = "FK_SUB_MATERIAL_CONSTRUCTION_SN"))
    val construction: Construction,

    @Column(name = "consumer_price", nullable = false)
    val consumerPrice: Int,

    @Column(name = "contractor_price", nullable = false)
    val contractorPrice: Int,

) {

    companion object {
        fun createSubMaterial(construction: Construction, rq: ConstructionRq): SubMaterial {
            return SubMaterial(
                construction = construction,
                consumerPrice = rq.subMaterialConsumerPrice,
                contractorPrice = rq.subMaterialContractorPrice
            )
        }
    }

    fun updateSubMaterial(rq: ConstructionRq): SubMaterial {
        return SubMaterial(
            sn = this.sn,
            construction = this.construction,
            consumerPrice = rq.subMaterialConsumerPrice,
            contractorPrice = rq.subMaterialContractorPrice
        )
    }
}