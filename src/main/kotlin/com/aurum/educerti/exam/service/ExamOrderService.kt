package com.aurum.educerti.exam.service

import com.aurum.educerti.exam.domain.ExamOrder
import com.aurum.educerti.exam.dto.CreateExamOrderRequest
import com.aurum.educerti.exam.repository.ExamOrderRepository
import com.aurum.educerti.exam.repository.ExamRepository
import com.aurum.educerti.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExamOrderService(
    private val memberRepository: MemberRepository,
    private val examRepository: ExamRepository,
    private val examOrderRepository: ExamOrderRepository
) {

    @Transactional
    fun createExamOrder(request: CreateExamOrderRequest): ExamOrder {
        val member = memberRepository.findById(request.memberId)
            .orElseThrow { IllegalArgumentException("Invalid member ID: ${request.memberId}") }

        val exam = examRepository.findById(request.examId)
            .orElseThrow { IllegalArgumentException("Invalid exam ID: ${request.examId}") }

        val examOrder = ExamOrder(
            exam = exam,
            member = member,
            status = request.status,
            totalAmount = request.totalAmount,
            cancelReason = null
        )

        return examOrderRepository.save(examOrder)
    }

    @Transactional(readOnly = true)
    fun getAllExamOrders(): List<ExamOrder> {
        return examOrderRepository.findAll()
    }
}
