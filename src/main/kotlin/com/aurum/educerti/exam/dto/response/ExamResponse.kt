package com.aurum.educerti.exam.dto.response

import com.aurum.educerti.exam.domain.Exam
import java.math.BigDecimal

data class ExamResponse(
    val id: Long?,
    val name: String,
    val category: String,
    val price: BigDecimal
) {
    constructor(exam: Exam) : this(
        id = exam.id,
        name = exam.name,
        category = exam.category,
        price = exam.price
    )
}