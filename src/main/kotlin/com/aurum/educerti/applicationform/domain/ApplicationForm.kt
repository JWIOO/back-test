package com.aurum.educerti.applicationform.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import com.aurum.educerti.exam.domain.Exam
import com.aurum.educerti.member.domain.Member


// 시험 원서 접수 정보 관리(결제 직전)
@Entity
@Table(name = "application_forms")
class ApplicationForm(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_form_id", nullable = false)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    var exam: Exam,

    @Column(name = "submitted_at", nullable = false)
    var submittedAt: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: ApplicationFormStatus = ApplicationFormStatus.PENDING
){
    fun updateStatus(status: ApplicationFormStatus){
        this.status = status
    }
}

enum class ApplicationFormStatus {
    PENDING, APPROVED, REJECTED
}
