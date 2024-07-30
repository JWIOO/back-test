package com.aurum.educerti.order.controller


import com.aurum.educerti.order.domain.OrderService
import com.aurum.educerti.order.dto.CreateOrderRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderApiController(private val orderService: OrderService) {

    @PostMapping("/api/orders")
    fun createOrder(@RequestBody request: CreateOrderRequest): OrderResponse {
        val order = orderService.createOrder(request)
        return OrderResponse(orderId = order.orderId)
    }

    @GetMapping("/api/orders")
    fun listOrders(): List<OrderResponse> {
        val orders = orderService.getAllOrders()
        return orders.map { OrderResponse(orderId = it.orderId) }
    }
}

data class OrderResponse(val orderId: Long?)
