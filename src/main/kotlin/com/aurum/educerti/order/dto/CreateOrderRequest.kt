package com.aurum.educerti.order.dto

data class CreateOrderRequest(
    val memberId: Long,
    val category: String,
    val productName: String,
    val paymentAmount: Double,
    val status: String
)

