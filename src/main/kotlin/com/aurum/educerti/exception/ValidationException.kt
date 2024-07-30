package com.aurum.educerti.exception

class ValidationException(val code: Int, override val message: String) : RuntimeException(message)
