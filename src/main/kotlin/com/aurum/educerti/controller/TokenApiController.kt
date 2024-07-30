package com.aurum.educerti.controller

import com.aurum.educerti.dto.CreateAccessTokenRequest
import com.aurum.educerti.service.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenApiController(private val tokenService: TokenService) {

    @PostMapping("/api/token")
    fun createNewAccessToken(@RequestBody request: CreateAccessTokenRequest): ResponseEntity<com.aurum.educerti.dto.CreateAccessTokenResponse> {
        val newAccessToken: String = tokenService.createNewAccessToken(request.refreshToken)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(com.aurum.educerti.dto.CreateAccessTokenResponse(newAccessToken))
    }
}
