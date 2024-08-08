package com.aurum.educerti.member.dto

import com.aurum.educerti.member.domain.Member
import com.aurum.educerti.exception.ValidationException
import com.aurum.educerti.exception.ErrorCode

data class AddMemberRequest(
    //이 값들을 받아와서
    val name: String,
    val password: String,
    val email: String,
    val address: String?,
    val phoneNumber: String?,
    val isAnonymous: Boolean,
) {
    //필드값 검증하자
    init {
        if (name.isBlank()) {
            throw ValidationException(ErrorCode.NAME_BLANK.code, ErrorCode.NAME_BLANK.message)
        }
        if (password.isBlank()) {
            throw ValidationException(ErrorCode.PASSWORD_BLANK.code, ErrorCode.PASSWORD_BLANK.message)
        }
        if (email.isBlank()) {
            throw ValidationException(ErrorCode.EMAIL_BLANK.code, ErrorCode.EMAIL_BLANK.message)
        }
        if (address?.isBlank() == true) {
            throw ValidationException(ErrorCode.ADDRESS_BLANK.code, ErrorCode.ADDRESS_BLANK.message)
        }
        if (phoneNumber?.isBlank() == true) {
            throw ValidationException(ErrorCode.PHONE_NUMBER_BLANK.code, ErrorCode.PHONE_NUMBER_BLANK.message)
        }
    }


    //엔티티로 치환
    fun toEntity() = Member(
        name = name,
        password = password,
        email = email,
        address = address,
        phoneNumber = phoneNumber,
        isAnonymous = isAnonymous
    )
}