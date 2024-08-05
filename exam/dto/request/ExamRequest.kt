package com.aurum.educerti.exam.dto.request

import com.aurum.educerti.exam.domain.Exam
import java.math.BigDecimal


//시험 주문 및 결제
//응시자가 시험을 주문하고 결제를 완료하면
//ExamRequest 를 사용하여 주문을 처리

data class ExamRequest(
    val name: String,
    val category: String,
    val price: BigDecimal,
) {
    fun toEntity(): Exam {
        return Exam(
            name = name,
            category = category,
            price = price,
        )
    }
}
