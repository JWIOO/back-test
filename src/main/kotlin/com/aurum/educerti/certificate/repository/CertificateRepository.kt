package com.aurum.educerti.certificate.repository

import com.aurum.educerti.certificate.domain.Certificate
import org.springframework.data.jpa.repository.JpaRepository

interface CertificateRepository : JpaRepository<Certificate, Long> {
    fun findByMemberId(memberId: Long): List<Certificate>
}