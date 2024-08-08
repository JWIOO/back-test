package com.aurum.educerti.common.domain

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.time.ZonedDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity {

    @field:CreationTimestamp
    @Column(nullable = false)
    lateinit var createdAt: ZonedDateTime

    @field:UpdateTimestamp
    @Column(nullable = false)
    lateinit var updatedAt: ZonedDateTime
}
