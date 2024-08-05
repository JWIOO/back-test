package com.aurum.educerti.member.dto

data class UpdateMemberRequest(
    val name: String,
    val username: String,
    val password: String,
    val email: String,
    val address: String?,
    val phoneNumber: String?,
    val isAnonymous: Boolean,
)