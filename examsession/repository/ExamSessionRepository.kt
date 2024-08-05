package com.aurum.educerti.certificate.repository

import com.aurum.educerti.examsession.domain.ExamSession
import org.springframework.data.jpa.repository.JpaRepository

interface ExamSessionRepository : JpaRepository<ExamSession, Long> {
    fun findByExamCode(examCode : String) : ExamSession?
}