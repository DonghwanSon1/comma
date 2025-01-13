package com.project.comma.domain.users

import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository: JpaRepository<Users, Long>, UsersCustomRepository {
  fun findByEmail(email: String): Users?
}