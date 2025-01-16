package com.project.comma.domain.construction.construction

import com.project.comma.domain.user.users.Users
import org.springframework.data.jpa.repository.JpaRepository

interface ConstructionRepository: JpaRepository<Construction, Long>, ConstructionCustomRepository {

  fun findBySnAndUser(sn: Long, user: Users): Construction?

}