package com.aurum.educerti.exam.dto

//시험 주문 및 결제
//응시자가 시험을 주문하고 결제를 완료하면
//CreateExamOrderRequest 를 사용하여 주문을 처리


data class CreateExamOrderRequest(
    val examId: Long,
    val memberId: Long,
    val status: String,
    val totalAmount: Double
)
