package com.aurum.educerti.applicationform.dto

import com.aurum.educerti.applicationform.domain.ApplicationForm
import com.aurum.educerti.applicationform.domain.ApplicationStatus
import com.aurum.educerti.exam.domain.Exam
import com.aurum.educerti.member.domain.Member

data class ApplicationFormRequest(
    val examId: Long,
    val memberId: Long
) {
    fun toEntity(exam: Exam, member: Member): ApplicationForm {
        return ApplicationForm(
            exam = exam,
            member = member,
            status = ApplicationStatus.PENDING
        )
    }
}
