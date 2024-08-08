package com.aurum.educerti.applicationform.dto.request

import com.aurum.educerti.applicationform.domain.ApplicationForm
import com.aurum.educerti.applicationform.domain.ApplicationFormStatus
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
            status = ApplicationFormStatus.PENDING
        )
    }
}
