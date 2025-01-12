package com.project.comma.domain.view

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {
  @GetMapping("/admin")
  fun adminPage(): String {
    return "admin" // templates/admin.html 파일을 렌더링
  }
}