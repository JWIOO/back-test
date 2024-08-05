package com.aurum.educerti.order.dto

import com.aurum.educerti.order.domain.Order
import com.aurum.educerti.member.domain.Member
import java.math.BigDecimal

data class OrderRequest(
    val memberId: Long,
    val productName: String,
    val category: String,
    val paymentAmount: BigDecimal
) {
    fun toEntity(member: Member): Order {
        return Order(
            member = member,
            productName = productName,
            category = category,
            paymentAmount = paymentAmount
        )
    }
}
