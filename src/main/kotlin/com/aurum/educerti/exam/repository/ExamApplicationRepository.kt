package com.aurum.educerti.exam.repository
import com.aurum.educerti.exam.domain.ExamApplication
import org.springframework.data.jpa.repository.JpaRepository

interface ExamApplicationRepository : JpaRepository<ExamApplication, Long>
