package com.aurum.educerti.order.controller

import com.aurum.educerti.order.dto.OrderRequest
import com.aurum.educerti.order.dto.response.OrderResponse
import com.aurum.educerti.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderApiController(private val orderService: OrderService) {


    // 전체 주문 조회
    @GetMapping
    fun getAllOrders(): ResponseEntity<List<OrderResponse>> {
        val orders = orderService.findAll()
        val response = orders.map { order ->
            OrderResponse(
                orderId = order.orderId,
                memberId = order.member.id!!,
                category = order.category,
                productName = order.productName,
                paymentAmount = order.paymentAmount.toDouble(),
                status = order.status,
                orderDate = order.orderDate,
                updatedAt = order.orderDate
            )
        }
        return ResponseEntity.ok(response)
    }

    // 특정 주문 조회
    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Long): ResponseEntity<OrderResponse> {
        val order = orderService.findById(id)
        val response = OrderResponse(
            orderId = order.orderId,
            memberId = order.member.id!!,
            category = order.category,
            productName = order.productName,
            paymentAmount = order.paymentAmount.toDouble(),
            status = order.status,
            orderDate = order.orderDate,
            updatedAt = order.orderDate
        )
        return ResponseEntity.ok(response)
    }


    // 주문 생성
    @PostMapping
    fun createOrder(@RequestBody request: OrderRequest): ResponseEntity<OrderResponse> {
        val savedOrder = orderService.save(request)
        val response = OrderResponse(
            orderId = savedOrder.orderId,
            memberId = savedOrder.member.id!!,
            category = savedOrder.category,
            productName = savedOrder.productName,
            paymentAmount = savedOrder.paymentAmount.toDouble(),
            status = savedOrder.status,
            orderDate = savedOrder.orderDate,
            updatedAt = savedOrder.orderDate // 초기 생성 시점에서는 updatedAt이 동일하게 설정될 수 있습니다.
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

//주문에서 업데이트가 필요 있나??
//    @PutMapping("/{id}")
//    fun updateOrder(
//        @PathVariable id: Long,
//        @RequestBody request: UpdateOrderRequest
//    ): ResponseEntity<Member> {
//        val updatedOrder = orderService.update(id, request)
//        return ResponseEntity.ok(updatedOrder)
//    }


    // 주문 삭제
    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id: Long): ResponseEntity<Void> {
        orderService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
