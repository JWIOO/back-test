package com.aurum.educerti.order.service

import com.aurum.educerti.applicationform.repository.ApplicationFormRepository
import com.aurum.educerti.order.domain.Order
import com.aurum.educerti.order.dto.request.OrderRequest
import com.aurum.educerti.order.repository.OrderRepository
import com.aurum.educerti.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val memberRepository: MemberRepository, // MemberRepository 추가
    private val applicationFormRepository: ApplicationFormRepository, // ApplicationFormRepository 추가
) {

    fun save(request: OrderRequest): Order {
        val member = memberRepository.findById(request.memberId)
            .orElseThrow { IllegalArgumentException("Member not found: ${request.memberId}") }
        val applicationForm = applicationFormRepository.findById(request.applicationFormId)
            .orElseThrow { IllegalArgumentException("ApplicationForm not found: ${request.memberId}") }

        return orderRepository.save(request.toEntity(member, applicationForm))
    }

    fun findAll(): List<Order> {
        return orderRepository.findAll()
    }

    fun findById(id: Long): Order {
        return orderRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Order not found: $id") }
    }

    fun delete(id: Long) {
        orderRepository.deleteById(id)
    }
}
