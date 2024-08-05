package com.aurum.educerti.certificate.repository

import com.aurum.educerti.applicationform.domain.ApplicationForm
import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationFormRepository : JpaRepository<ApplicationForm, Long> {
    fun findByMemberId(memberId: Long): List<ApplicationForm>
}