package com.aurum.educerti.order.domain

import jakarta.persistence.*
import com.aurum.educerti.member.domain.Member
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.time.LocalDateTime

// application form (원서 접수) 시에 생성된 주문 정보 관리
@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member,

    @Column(name = "product_name", nullable = false)
    val productName: String,

    @Column(name = "category", nullable = false)
    val category: String,

    @Column(name = "payment_amount", nullable = false)
    val paymentAmount: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: OrderStatus = OrderStatus.PENDING,

    @CreatedDate
    @Column(name = "order_date", updatable = false)
    var orderDate: LocalDateTime? = LocalDateTime.now()
)

enum class OrderStatus {
    PENDING, COMPLETED, CANCELLED
}
