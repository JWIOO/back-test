package com.aurum.educerti.certificate.service

import com.aurum.educerti.certificate.domain.Certificate
import com.aurum.educerti.certificate.repository.CertificateRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CertificateService(private val certificateRepository: CertificateRepository) {

    // Create
    @Transactional
    fun save(certificate: Certificate): Certificate {
        return certificateRepository.save(certificate)
    }

    // Read
    @Transactional(readOnly = true)
    fun findById(id: Long): Certificate {
        return certificateRepository.findById(id).orElseThrow { RuntimeException("Certificate not found") }
    }

    // Update
    @Transactional
    fun updateCertificate(id: Long, updatedCertificate: Certificate): Certificate {
        val certificate = findById(id)
        certificate.apply {
            this.member = updatedCertificate.member
            this.exam = updatedCertificate.exam
            this.issueDate = updatedCertificate.issueDate
            this.certificatePath = updatedCertificate.certificatePath
        }
        return certificateRepository.save(certificate)
    }

    // Delete
    @Transactional
    fun deleteById(id: Long) {
        certificateRepository.deleteById(id)
    }
}

