package com.aurum.educerti.applicationform.service

import com.aurum.educerti.applicationform.domain.ApplicationForm
import com.aurum.educerti.applicationform.domain.ApplicationFormStatus
import com.aurum.educerti.applicationform.dto.request.ApplicationFormRequest
import com.aurum.educerti.applicationform.repository.ApplicationFormRepository
import com.aurum.educerti.exam.repository.ExamRepository
import com.aurum.educerti.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class ApplicationFormService(
    private val applicationFormRepository: ApplicationFormRepository,
    private val memberRepository: MemberRepository,
    private val examRepository: ExamRepository,
) {

    fun save(request: ApplicationFormRequest): ApplicationForm {
        val exam = examRepository.findById(request.examId)
            .orElseThrow { IllegalArgumentException("Exam not found: ${request.examId}") }

        val member = memberRepository.findById(request.memberId)
            .orElseThrow { IllegalArgumentException("Member not found: ${request.memberId}") }

        return applicationFormRepository.save(
            request.toEntity(
                exam = exam,
                member = member,
            )
        )
    }

    fun findAll(): List<ApplicationForm> {
        return applicationFormRepository.findAll()
    }

    fun findById(id: Long): ApplicationForm {
        return applicationFormRepository.findById(id)
            .orElseThrow { IllegalArgumentException("ApplicationForm not found: $id") }
    }

    fun findByMemberId(memberId: Long): List<ApplicationForm> {
        return applicationFormRepository.findByMemberId(memberId)
    }

    @Transactional
    fun updateStatus(id: Long, status: ApplicationFormStatus): ApplicationForm {
        val applicationForm = applicationFormRepository.findById(id)
            .orElseThrow { IllegalArgumentException("ApplicationForm not found: $id") }

        applicationForm.updateStatus(status)

        return applicationFormRepository.save(applicationForm)
    }

    fun delete(id: Long) {
        val applicationForm = applicationFormRepository.findById(id)
            .orElseThrow { IllegalArgumentException("ApplicationForm not found: $id") }
        applicationFormRepository.deleteById(applicationForm.id!!)
    }

}