package com.aurum.educerti.exam.dto

// 수험코드 입력 및 시험 응시 시작
// 결제가 완료된 후, 응시자가 수험코드를 입력하여 시험을 시작
// 이때 StartExamRequest DTO 를 사용하여 수험코드를 전달하고,
// ExamApplicationService 에서 이를 검증하여 시험 응시를 시작
data class StartExamRequest(
    val examOrderId: Long,
    val examCode: String
)