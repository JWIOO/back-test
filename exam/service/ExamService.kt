package com.aurum.educerti.exam.service

import com.aurum.educerti.exam.domain.Exam
import com.aurum.educerti.exam.dto.request.ExamRequest
import com.aurum.educerti.exam.repository.ExamRepository
import org.springframework.stereotype.Service

@Service
class ExamService(
    private val examRepository: ExamRepository
) {

    fun save(request: ExamRequest): Exam {
        return examRepository.save(request.toEntity())
    }

    fun findAll(): List<Exam> {
        return examRepository.findAll()
    }

    fun findById(id: Long): Exam {
        return examRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Exam not found: $id") }
    }

    fun delete(id: Long) {
        examRepository.deleteById(id)
    }
}
