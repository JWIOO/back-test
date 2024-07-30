package com.aurum.educerti.exam.service

import com.aurum.educerti.exam.repository.ExamOrderRepository
import com.aurum.educerti.exam.domain.ExamApplication
import com.aurum.educerti.exam.repository.ExamApplicationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExamApplicationService(
    private val examApplicationRepository: ExamApplicationRepository,
    private val examOrderRepository: ExamOrderRepository
) {

    @Transactional
    fun startExam(examOrderId: Long, examCode: String): ExamApplication {
        val examOrder = examOrderRepository.findById(examOrderId)
            .orElseThrow { IllegalArgumentException("Invalid exam order ID: $examOrderId") }

        // 수험코드 검증 로직 추가 (예: 수험코드가 올바른지 확인)
        if (examOrder.exam.code != examCode) {
            throw IllegalArgumentException("Invalid exam code")
        }

        val examApplication = ExamApplication(
            examOrder = examOrder,
            member = examOrder.member,
            result = null
        )

        return examApplicationRepository.save(examApplication)
    }

    @Transactional(readOnly = true)
    fun getAllExamApplications(): List<ExamApplication> {
        return examApplicationRepository.findAll()
    }
}
