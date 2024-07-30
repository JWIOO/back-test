package com.aurum.educerti.domain

import jakarta.persistence.*

@Entity
class RefreshToken(
    @Column(name = "user_id", nullable = false, unique = true)
    val userId: Long,

    @Column(name = "refresh_token", nullable = false)
    var refreshToken: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    val id: Long? = null

    fun update(newRefreshToken: String): RefreshToken {
        this.refreshToken = newRefreshToken
        return this
    }

    // 기본 생성자를 사용하여 @NoArgsConstructor 대체
    constructor() : this(0L, "")
}
