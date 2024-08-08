package com.aurum.educerti.examsession.service


import com.aurum.educerti.examsession.repository.ExamSessionRepository
import com.aurum.educerti.exam.dto.ExamSessionRequest
import com.aurum.educerti.exam.repository.ExamRepository
import com.aurum.educerti.examsession.domain.ExamSession
import com.aurum.educerti.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ExamSessionService(
    private val examSessionRepository: ExamSessionRepository,
    private val examRepository: ExamRepository,
    private val memberRepository: MemberRepository
) {

    fun save(request: ExamSessionRequest): ExamSession {
        val exam = examRepository.findById(request.examId)
            .orElseThrow { IllegalArgumentException("Exam not found: ${request.examId}") }
        val member = memberRepository.findById(request.memberId)
            .orElseThrow { IllegalArgumentException("Member not found: ${request.memberId}") }
        return examSessionRepository.save(request.toEntity(exam, member))
    }

    fun findAll(): List<ExamSession> {
        return examSessionRepository.findAll()
    }

    fun findById(id: Long): ExamSession {
        return examSessionRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Exam session not found: $id") }
    }

    @Transactional
    fun submitFirstFile(sessionId: Long, filePath: String): ExamSession {
        val session = findById(sessionId)
        session.firstFilePath = filePath
        return examSessionRepository.save(session)
    }

    @Transactional
    fun submitSecondFile(sessionId: Long, filePath: String): ExamSession {
        val session = findById(sessionId)
        session.secondFilePath = filePath
        return examSessionRepository.save(session)
    }


    fun delete(id: Long) {
        examSessionRepository.deleteById(id)
    }
}
