package com.project.comma.common.authority

import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import com.project.comma.common.response.CustomUser
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*


// TODO 토큰 만료 시 RefreshToken 발급할 수 있도록 수정 필요.!
@Component
class JwtTokenProvider {
    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    @Value("\${jwt.access-token-expire-time}")
    private var accessTokenExpireTime: Long = 0

    private val key by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)) }

    /**
     * Token 생성
     */
    fun createToken(authentication: Authentication): TokenInfo {
        val authorities: String = authentication
            .authorities
            .joinToString(",", transform = GrantedAuthority::getAuthority)

        val now = Date()
        val accessExpiration = Date(now.time + accessTokenExpireTime)

        val accessToken = Jwts
            .builder()
            .setSubject(authentication.name)
            .claim("role", authorities)
            .claim("userSn", (authentication.principal as CustomUser).sn)
            .setIssuedAt(now)
            .setExpiration(accessExpiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        return TokenInfo("Bearer", accessToken)
    }

    /**
     * Token 정보 추출
     */
    fun getAuthentication(token: String): Authentication {
        val claims: Claims = getClaims(token)

        val auth = claims["role"] ?: throw CommonException(CommonExceptionCode.INVALID_TOKEN)
        val userId = claims["userSn"] ?: throw CommonException(CommonExceptionCode.INVALID_TOKEN)

        // 권한 정보 추출
        val authorities: Collection<GrantedAuthority> = (auth as String)
            .split(",")
            .map { SimpleGrantedAuthority(it) }

        val principal: UserDetails = CustomUser(userId.toString().toLong(), claims.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    /**
     * Token 검증
     */
    fun validateToken(token: String): Boolean {
        try {
            getClaims(token)
            return true
        } catch (e: Exception) {
            when (e) {
                is SecurityException -> {}
                is MalformedJwtException -> {}
                is ExpiredJwtException -> {}
                is UnsupportedJwtException -> {}
                is IllegalArgumentException -> {}
                else -> {}
            }
            println(e.message)
        }
        return false
    }

    /**
     * UserSn 값 추출하기
     */
    fun getUserSnFromToken(token: String): TokenExtraction {
        val cleanedToken = token.trim().takeIf { it.startsWith("Bearer") }
            ?.substring(7)
            ?: token

        val claims: Claims = getClaims(cleanedToken)
        val userSn: Long = claims["userSn"]?.toString()?.toLong() ?: throw CommonException(CommonExceptionCode.INVALID_TOKEN)
        val role: String = claims["role"]?.toString() ?: throw CommonException(CommonExceptionCode.INVALID_TOKEN)

        return TokenExtraction(userSn, role)
    }

    private fun getClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
}