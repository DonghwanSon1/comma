package com.project.comma.common.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

  override fun addCorsMappings(registry: CorsRegistry) {
    registry.addMapping("/**")
      .allowedOrigins("http://http://dh-project.mooo.com:8090") // 요청을 허용할 출처
      .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
      .allowedHeaders("*") // 모든 요청 헤더를 허용
      .exposedHeaders("Authorization") // 클라이언트에서 사용할 응답 헤더
      .allowCredentials(true) // 자격 증명(쿠키, 인증 정보)을 포함할 수 있도록 허용
      .maxAge(3600) // 프리플라이트 요청 캐시 시간을 1시간으로 설정
  }
}