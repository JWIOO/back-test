package com.aurum.educerti.certificate.domain

import com.aurum.educerti.common.domain.BaseEntity
import com.aurum.educerti.exam.domain.Exam
import com.aurum.educerti.member.domain.Member
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "certificates")
class Certificate(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val certificateId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    var exam: Exam,

    @Column(name = "issue_date", nullable = false)
    var issueDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "certificate_path", nullable = false)
    var certificatePath: String // 자격증 파일 경로
) : BaseEntity()
