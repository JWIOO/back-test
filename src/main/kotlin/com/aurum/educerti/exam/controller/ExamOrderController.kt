
package com.aurum.educerti.controller

import com.aurum.educerti.exam.dto.CreateExamOrderRequest
import com.aurum.educerti.exam.service.ExamOrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ExamOrderController(private val examOrderService: ExamOrderService) {

    @PostMapping("/api/exam-orders")
    fun createExamOrder(@RequestBody request: CreateExamOrderRequest): ExamOrderResponse {
        val order = examOrderService.createExamOrder(request)
        return ExamOrderResponse(order.id)
    }

    @GetMapping("/api/exam-orders")
    fun listExamOrders(): List<ExamOrderResponse> {
        val orders = examOrderService.getAllExamOrders()
        return orders.map { ExamOrderResponse(it.id) }
    }
}

data class ExamOrderResponse(val orderId: Long?)
