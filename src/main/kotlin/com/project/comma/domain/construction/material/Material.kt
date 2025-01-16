package com.project.comma.domain.construction.material

import com.project.comma.domain.construction.construction.Construction
import com.project.comma.domain.construction.construction.rqrs.ConstructionRq
import javax.persistence.*

@Entity
@Table(name = "material")
class Material(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    val sn: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "construction_sn", nullable = false, foreignKey = ForeignKey(name = "FK_MATERIAL_CONSTRUCTION_SN"))
    val construction: Construction,

    @Column(name = "quantity", nullable = false)
    val quantity: Int,

    @Column(name = "consumer_price", nullable = false)
    val consumerPrice: Int,

    @Column(name = "contractor_price", nullable = false)
    val contractorPrice: Int,

) {
    companion object {
        fun createMaterial(construction: Construction, rq: ConstructionRq): Material {
            return Material(
                construction = construction,
                quantity = rq.quantity,
                consumerPrice = rq.materialConsumerPrice,
                contractorPrice = rq.materialContractorPrice
            )
        }
    }

    fun updateMaterial(rq: ConstructionRq): Material {
        return Material(
            sn = this.sn,
            construction = this.construction,
            quantity = rq.quantity,
            consumerPrice = rq.materialConsumerPrice,
            contractorPrice = rq.materialContractorPrice
        )
    }
}