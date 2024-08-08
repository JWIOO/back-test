package com.aurum.educerti.payment.domain

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.time.LocalDateTime
import com.aurum.educerti.order.domain.Order

// 주문에 대한 결제 정보 관리
@Entity
@Table(name = "payments")
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val paymentId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    val order: Order,

    @Column(name = "payment_amount", nullable = false)
    val paymentAmount: BigDecimal,

    @Column(name = "payment_date", nullable = false)
    val paymentDate: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: PaymentStatus = PaymentStatus.PENDING
)

enum class PaymentStatus {
    PENDING, APPROVED, REJECTED
}
