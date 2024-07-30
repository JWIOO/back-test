package com.aurum.educerti.repository

import com.aurum.educerti.domain.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RefreshTokenRepository : JpaRepository<RefreshToken, Long> {
    fun findByUserId(userId: Long): Optional<RefreshToken>
    fun findByRefreshToken(refreshToken: String): Optional<RefreshToken>
}
