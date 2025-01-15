package com.project.comma.domain.userRole


import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
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

  @Transactional
  fun deleteRole(user: Users) {
    val userRole: UsersRole = usersRoleRepository.findByUserSn(user.sn!!) ?: throw CommonException(CommonExceptionCode.NOT_EXIST_USER_ID)
      usersRoleRepository.delete(userRole)
  }

}