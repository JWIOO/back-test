package com.aurum.educerti.applicationform.dto.response

import com.aurum.educerti.applicationform.domain.ApplicationForm
import com.aurum.educerti.applicationform.domain.ApplicationFormStatus
import com.aurum.educerti.exam.dto.response.ExamResponse
import com.aurum.educerti.member.dto.MemberResponse
import java.time.LocalDateTime


data class ApplicationFormResponse(
    val id: Long?,
    val member: MemberResponse.MemberDto,
    val exam: ExamResponse,
    val submittedAt: LocalDateTime,
    val status: ApplicationFormStatus
) {
    constructor(applicationForm: ApplicationForm) : this(
        id = applicationForm.id,
        member = MemberResponse.MemberDto(applicationForm.member),
        exam = ExamResponse(applicationForm.exam),
        submittedAt = applicationForm.submittedAt,
        status = applicationForm.status
    )
}