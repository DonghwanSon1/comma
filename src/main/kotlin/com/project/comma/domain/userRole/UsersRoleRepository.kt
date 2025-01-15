package com.project.comma.domain.userRole

import org.springframework.data.jpa.repository.JpaRepository

interface UsersRoleRepository: JpaRepository<UsersRole, Long> {

  fun findByUserSn(userSn: Long): UsersRole?

}