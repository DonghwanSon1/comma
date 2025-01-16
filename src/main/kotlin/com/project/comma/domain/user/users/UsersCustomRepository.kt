package com.project.comma.domain.user.users

import com.project.comma.domain.user.users.rqrs.UserDto

interface UsersCustomRepository {
  fun findUserWithRoles(email: String): UserDto?
}