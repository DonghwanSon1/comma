package com.project.comma.domain.user.users

import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository: JpaRepository<Users, Long>, UsersCustomRepository {
  fun findByEmail(email: String): Users?
}