package com.aurum.educerti.order.dto.request

import com.aurum.educerti.applicationform.domain.ApplicationForm
import com.aurum.educerti.order.domain.Order
import com.aurum.educerti.member.domain.Member
import java.math.BigDecimal

data class OrderRequest(
    val memberId: Long,
    val applicationFormId : Long,
    val productName: String,
    val category: String,
    val paymentAmount: BigDecimal
) {
    fun toEntity(member: Member, applicationForm: ApplicationForm): Order {
        return Order(
            member = member,
            applicationForm = applicationForm,
            productName = productName,
            category = category,
            paymentAmount = paymentAmount
        )
    }
}
