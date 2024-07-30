package com.aurum.educerti.exception


enum class ErrorCode(val code: Int, val message: String) {
    NAME_BLANK(400, "이름이 빈 칸이면 안돼요"),
    USERNAME_BLANK(400, "아이디가 빈 칸이면 안돼요"),
    PASSWORD_BLANK(400, "비밀번호가 빈 칸이면 안돼요"),
    EMAIL_BLANK(400, "이메일이 빈 칸이면 안돼요"),
    ADDRESS_BLANK(400, "주소가 빈 칸이면 안돼요"),
    PHONE_NUMBER_BLANK(400, "전화번호가 빈 칸이면 안돼요")
}