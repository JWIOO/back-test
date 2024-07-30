package com.aurum.educerti.controller

import com.aurum.educerti.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserApiController(private val userService: UserService) {

    @PostMapping("/user")
    fun signup(request: com.aurum.educerti.dto.AddUserRequest): String {
        userService.save(request)
        return "redirect:/login"
    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        SecurityContextLogoutHandler().logout(
            request, response,
            SecurityContextHolder.getContext().authentication
        )
        return "redirect:/login"
    }
}
