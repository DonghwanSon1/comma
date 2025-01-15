package com.project.comma.domain.users

import com.project.comma.common.authority.JwtTokenProvider
import com.project.comma.common.authority.TokenExtraction
import com.project.comma.common.authority.TokenInfo
import com.project.comma.common.response.BaseResponse
import com.project.comma.common.response.CustomUser
import com.project.comma.domain.userRole.enums.Role
import com.project.comma.domain.users.rqrs.LoginRq
import com.project.comma.domain.users.rqrs.UserRq
import com.project.comma.domain.users.rqrs.UserRs
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "유저 관련 API")
class UsersController(
    private val usersService: UsersService,
    private val jwtTokenProvider: JwtTokenProvider
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

  /**
   * 회원 정보 조회 API
   */
  @GetMapping("/info")
  fun userInfo(@RequestHeader("Authorization") auth: String): BaseResponse<UserRs> {
    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    return BaseResponse(data = usersService.userInfo(tokenExtraction.userSn))
  }

  /**
   * 회원 정보 수정 API
   */
  @PatchMapping()
  fun userUpdate(@RequestHeader("Authorization") auth: String,
                 @RequestBody userRq: UserRq): BaseResponse<UserRs> {
    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    val resultMsg: String = usersService.userUpdate(tokenExtraction.userSn, userRq)
    return BaseResponse(message = resultMsg)
  }

  /**
   * 회원탈퇴 API
   */
  @DeleteMapping("/withdraw")
  fun deleteUser(@RequestHeader("Authorization") auth: String): BaseResponse<Unit> {
    val tokenExtraction: TokenExtraction = jwtTokenProvider.getUserSnFromToken(auth)
    val resultMsg: String = usersService.deleteUser(tokenExtraction.userSn)
    return BaseResponse(message = resultMsg)
  }

}