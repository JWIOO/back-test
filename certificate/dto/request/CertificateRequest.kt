package com.aurum.educerti.certificate.dto.request

import com.aurum.educerti.certificate.domain.Certificate
import com.aurum.educerti.exam.domain.Exam
import com.aurum.educerti.member.domain.Member
import java.time.LocalDateTime

data class CertificateRequest(
    val memberId: Long,
    val examId: Long,
    val certificatePath: String
) {
    fun toEntity(member: Member, exam: Exam): Certificate {
        return Certificate(
            member = member,
            exam = exam,
            issueDate = LocalDateTime.now(),
            certificatePath = certificatePath
        )
    }
}
