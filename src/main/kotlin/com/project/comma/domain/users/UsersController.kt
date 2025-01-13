package com.project.comma.domain.users

import com.project.comma.common.authority.TokenInfo
import com.project.comma.common.response.BaseResponse
import com.project.comma.domain.userRole.enums.Role
import com.project.comma.domain.users.rqrs.LoginRq
import com.project.comma.domain.users.rqrs.UserRq
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "유저 관련 API")
class UsersController(
    private val usersService: UsersService
) {

  /**
   * 관리자 회원가입 API
   */
  @PostMapping("/admin/signup")
  fun teacherSignUp(@RequestBody userRq: UserRq): BaseResponse<Unit> {
    val resultMsg: String = usersService.signUp(userRq, Role.ADMIN)
    return BaseResponse(message = resultMsg)
  }

  /**
   * 사용자 회원가입 API
   */
  @PostMapping("/signup")
  fun studentSignUp(@RequestBody userRq: UserRq): BaseResponse<Unit> {
    val resultMsg: String = usersService.signUp(userRq, Role.USER)
    return BaseResponse(message = resultMsg)
  }

  /**
   * 로그인 (토큰 발급) API
   */
  @PostMapping("/login")
  fun login(@RequestBody loginRq: LoginRq): BaseResponse<TokenInfo> {
    val tokenInfo = usersService.login(loginRq)
    return BaseResponse(data = tokenInfo)
  }

}