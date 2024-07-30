package com.aurum.educerti.exam.repository


import com.aurum.educerti.exam.domain.Exam
import org.springframework.data.jpa.repository.JpaRepository

interface ExamRepository : JpaRepository<Exam, Long>
