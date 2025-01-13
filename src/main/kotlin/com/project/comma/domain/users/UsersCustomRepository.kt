package com.project.comma.domain.users

import com.project.comma.domain.users.rqrs.UserRs

interface UsersCustomRepository {
  fun findUserWithRoles(email: String): UserRs?
}