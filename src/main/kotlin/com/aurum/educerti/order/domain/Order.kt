package com.aurum.educerti.order.domain

import jakarta.persistence.*
import com.aurum.educerti.member.domain.Member
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "orders")
class Order(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,

    @Column(name = "category", nullable = false)
    var category: String, // "시험", "자격증", "과목"

    @Column(name = "product_name", nullable = false)
    var productName: String,

    @Column(name = "payment_amount", nullable = false)
    var paymentAmount: Double,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: OrderStatus,

    @Column(name = "cancel_reason")
    var cancelReason: String? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false)
    val orderId: Long? = null

    @CreatedDate
    @Column(name = "order_date", updatable = false)
    var orderDate: LocalDateTime? = null
        private set

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
        private set


    // 주문 상태를 업데이트하는 메서드
    fun updateStatus(newStatus: OrderStatus) {
        this.status = newStatus
    }

    // 주문을 취소하고 취소 사유를 설정하는 메서드
    fun cancelOrder(reason: String) {
        this.status = OrderStatus.CANCELLED
        this.cancelReason = reason
    }
}
