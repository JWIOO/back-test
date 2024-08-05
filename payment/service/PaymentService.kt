package com.aurum.educerti.service

import com.aurum.educerti.payment.repository.PaymentRepository
import com.aurum.educerti.payment.domain.Payment
import com.aurum.educerti.payment.dto.PaymentRequest
import com.aurum.educerti.order.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val orderRepository: OrderRepository
) {

    fun save(request: PaymentRequest): Payment {
        val order = orderRepository.findById(request.orderId)
            .orElseThrow { IllegalArgumentException("Order not found: ${request.orderId}") }

        val payment = request.toEntity(order)
        return paymentRepository.save(payment)
    }

    fun findAll(): List<Payment> {
        return paymentRepository.findAll()
    }

    fun findById(id: Long): Payment {
        return paymentRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Payment not found: $id") }
    }

    fun delete(id: Long) {
        paymentRepository.deleteById(id)
    }
}
