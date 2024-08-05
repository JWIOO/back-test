package com.aurum.educerti.order.dto.response

import com.aurum.educerti.order.domain.OrderStatus
import java.time.LocalDateTime

data class OrderResponse(
    val orderId: Long?,
    val memberId: Long,
    val category: String,
    val productName: String,
    val paymentAmount: Double,
    val status: OrderStatus,
    val orderDate: LocalDateTime?,
    val updatedAt: LocalDateTime?
)
