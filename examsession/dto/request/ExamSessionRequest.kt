package com.aurum.educerti.exam.dto

import com.aurum.educerti.examsession.domain.ExamSession
import com.aurum.educerti.exam.domain.Exam
import com.aurum.educerti.member.domain.Member

data class ExamSessionRequest(
    val examId: Long,
    val memberId: Long,
    val examCode: String
) {
    fun toEntity(exam: Exam, member: Member): ExamSession {
        return ExamSession(
            exam = exam,
            member = member,
            examCode = examCode
        )
    }
}
