package com.aurum.educerti.order.dto.response

import com.aurum.educerti.order.domain.Order
import com.aurum.educerti.order.domain.OrderStatus
import java.time.LocalDateTime

data class OrderResponse(
    val orderId: Long?,
    val memberId: Long,
    val applicationFormId: Long,
    val category: String,
    val productName: String,
    val paymentAmount: Double,
    val status: OrderStatus,
    val orderDate: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {
    constructor(order: Order) : this(
        orderId = order.orderId,
        memberId = order.member.id!!,
        applicationFormId = order.applicationForm.id!!,
        category = order.category,
        productName = order.productName,
        paymentAmount = order.paymentAmount.toDouble(),
        status = order.status,
        orderDate = order.orderDate,
        updatedAt = order.orderDate
    )
}
