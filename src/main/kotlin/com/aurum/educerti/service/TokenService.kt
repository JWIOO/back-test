package com.aurum.educerti.service

import com.aurum.educerti.config.jwt.TokenProvider
import com.aurum.educerti.domain.User
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class TokenService(
    private val tokenProvider: TokenProvider,
    private val refreshTokenService: RefreshTokenService,
    private val userService: UserService
) {

    fun createNewAccessToken(refreshToken: String): String {
        require(tokenProvider.validToken(refreshToken)) { "Unexpected token" }

        val userId: Long = refreshTokenService.findByRefreshToken(refreshToken).userId
        val user: User = userService.findById(userId)

        return tokenProvider.generateToken(user, Duration.ofHours(2))
    }
}
