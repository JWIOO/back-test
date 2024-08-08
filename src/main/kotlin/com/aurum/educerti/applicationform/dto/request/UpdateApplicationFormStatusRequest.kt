package com.aurum.educerti.applicationform.dto.request

import com.aurum.educerti.applicationform.domain.ApplicationFormStatus

data class UpdateApplicationFormStatusRequest(
    val id: Long,
    val status: ApplicationFormStatus
)