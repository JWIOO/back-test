package com.aurum.educerti.order.dto.request

import java.math.BigDecimal

data class UpdateOrderRequest (

    val memberId: Long,
    val productName: String,
    val category: String,
    val paymentAmount: BigDecimal

)



