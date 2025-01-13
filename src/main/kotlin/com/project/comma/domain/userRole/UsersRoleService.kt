package com.project.comma.domain.userRole


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.project.comma.domain.userRole.enums.Role
import com.project.comma.domain.users.Users

@Service
@Transactional(readOnly = true)
class UsersRoleService(
  private val usersRoleRepository: UsersRoleRepository,
) {

  @Transactional
  fun signUpRole(user: Users, role: Role) {
    usersRoleRepository.save(UsersRole(null, user, role.name))
  }

}