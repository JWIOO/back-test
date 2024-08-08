package com.aurum.educerti.examsession.repository

import com.aurum.educerti.examsession.domain.ExamSession
import org.springframework.data.jpa.repository.JpaRepository

interface ExamSessionRepository : JpaRepository<ExamSession, Long> {
    fun findByExamCode(examCode : String) : ExamSession?
}