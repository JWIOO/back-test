package com.aurum.educerti.payment.dto

import com.aurum.educerti.payment.domain.Payment
import com.aurum.educerti.order.domain.Order
import com.aurum.educerti.payment.domain.PaymentStatus
import java.math.BigDecimal
import java.time.LocalDateTime

data class PaymentRequest(
    val orderId: Long,
    val paymentAmount: BigDecimal
) {
    fun toEntity(order: Order): Payment {
        return Payment(
            order = order,
            paymentAmount = paymentAmount,
            paymentDate = LocalDateTime.now(),
            status = PaymentStatus.PENDING
        )
    }
}
