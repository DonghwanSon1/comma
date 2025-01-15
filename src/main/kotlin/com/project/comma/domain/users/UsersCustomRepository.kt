package com.project.comma.domain.users

import com.project.comma.domain.users.rqrs.UserDto

interface UsersCustomRepository {
  fun findUserWithRoles(email: String): UserDto?
}