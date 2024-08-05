package com.aurum.educerti.examsession.domain

import com.aurum.educerti.common.domain.BaseEntity
import com.aurum.educerti.exam.domain.Exam
import com.aurum.educerti.member.domain.Member
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "exam_sessions")
class ExamSession(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id", nullable = false)
    val sessionId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    var exam: Exam,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,

    @Column(name = "exam_code", nullable = false)
    var examCode: String, // 수험번호

    @Column(name = "start_time", nullable = false)
    var startTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "end_time")
    var endTime: LocalDateTime? = null,

    @Column(name = "first_file_path")
    var firstFilePath: String? = null,

    @Column(name = "second_file_path")
    var secondFilePath: String? = null,

    @Column(name = "is_expired", nullable = false)
    var isExpired: Boolean = false
) : BaseEntity()
