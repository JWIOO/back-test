package com.aurum.educerti.service

import com.aurum.educerti.domain.RefreshToken
import com.aurum.educerti.repository.RefreshTokenRepository
import org.springframework.stereotype.Service

@Service
class RefreshTokenService(
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun findByRefreshToken(refreshToken: String): RefreshToken {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
            .orElseThrow { IllegalArgumentException("Unexpected token") }
    }
}
