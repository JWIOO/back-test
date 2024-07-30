package com.aurum.educerti.service

import com.aurum.educerti.domain.User
import com.aurum.educerti.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    fun save(dto: com.aurum.educerti.dto.AddUserRequest): Long {
        val user = User(
            email = dto.email ?: "",
            password = bCryptPasswordEncoder.encode(dto.password)
        )
        return userRepository.save(user).id ?: throw IllegalStateException("User ID should not be null")
    }

    fun findById(userId: Long): User {
        return userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("Unexpected user") }
    }
}
