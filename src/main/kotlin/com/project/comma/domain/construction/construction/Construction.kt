package com.project.comma.domain.construction.construction

import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import com.project.comma.domain.brand.Brand
import com.project.comma.domain.construction.construction.rqrs.ConstructionDto
import com.project.comma.domain.construction.construction.rqrs.ConstructionRq
import com.project.comma.domain.film.Film
import com.project.comma.domain.film.rqrs.FilmRq
import com.project.comma.domain.user.users.Users
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "construction")
class Construction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    val sn: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sn", nullable = false, foreignKey = ForeignKey(name = "FK_CONSTRUCTION_USER_SN"))
    val user: Users,

    @Column(name = "location", nullable = false)
    val location: String,

    @Column(name = "total_personnel", nullable = false)
    val totalPersonnel: Int,

    @Column(name = "total_labor_cost", nullable = false)
    val totalLaborCost: Int,

    @Column(name = "total_consumer_cost", nullable = false)
    val totalConsumerCost: Int,

    @Column(name = "total_contractor_cost", nullable = false)
    val totalContractorCost: Int,

    @Column(name = "total_meal_cost", nullable = false)
    val totalMealCost: Int,

    @Column(name = "start_date", nullable = false)
    val startDate: LocalDate,

    @Column(name = "start_time", nullable = false)
    val startTime: LocalTime,
) {

    companion object {
        fun createConstruction(dto: ConstructionDto): Construction {
            return Construction(
                user = dto.user,
                location = dto.location,
                totalPersonnel = dto.totalPersonnel,
                totalLaborCost = dto.totalLaborCost,
                totalConsumerCost = dto.totalConsumerCost,
                totalContractorCost = dto.totalContractorCost,
                totalMealCost = dto.totalMealCost,
                startDate = dto.startDate,
                startTime = dto.startTime
            )
        }
    }

    fun updateConstruction(dto: ConstructionDto): Construction {
        return Construction(
            sn = this.sn,
            user = this.user,
            location = dto.location,
            totalPersonnel = dto.totalPersonnel,
            totalLaborCost = dto.totalLaborCost,
            totalConsumerCost = dto.totalConsumerCost,
            totalContractorCost = dto.totalContractorCost,
            totalMealCost = dto.totalMealCost,
            startDate = dto.startDate,
            startTime = dto.startTime
        )
    }
}