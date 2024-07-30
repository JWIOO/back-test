package com.aurum.educerti.order.domain

import com.aurum.educerti.member.repository.MemberRepository
import com.aurum.educerti.order.repository.OrderRepository
import com.aurum.educerti.order.dto.CreateOrderRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val memberRepository: MemberRepository,
    private val orderRepository: OrderRepository
) {

    @Transactional
    fun createOrder(request: CreateOrderRequest): Order {
        val member = memberRepository.findById(request.memberId)
            .orElseThrow { IllegalArgumentException("Invalid member ID: ${request.memberId}") }

        val order = Order(
            member = member,
            category = request.category,
            productName = request.productName,
            paymentAmount = request.paymentAmount,
            status = OrderStatus.PENDING
        )

        return orderRepository.save(order)
    }

    @Transactional(readOnly = true)
    fun getAllOrders(): List<Order> {
        return orderRepository.findAll()
    }
}
