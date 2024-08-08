package com.aurum.educerti.refund.domain

import jakarta.persistence.*
import com.aurum.educerti.order.domain.Order
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "refunds")
class Refund(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order,

    @Column(name = "refund_amount", nullable = false)
    var refundAmount: Double,

    @Column(name = "refund_reason", nullable = false)
    var refundReason: String,

    @Column(name = "status", nullable = false)
    var status: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    val id: Long? = null

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
        private set

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
        private set
}
