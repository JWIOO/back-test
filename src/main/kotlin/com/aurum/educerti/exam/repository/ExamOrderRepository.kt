package com.aurum.educerti.exam.repository


import com.aurum.educerti.exam.domain.ExamOrder
import org.springframework.data.jpa.repository.JpaRepository

interface ExamOrderRepository : JpaRepository<ExamOrder, Long>
