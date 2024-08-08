package com.aurum.educerti.order.controller

import com.aurum.educerti.order.dto.request.OrderRequest
import com.aurum.educerti.order.dto.response.OrderResponse
import com.aurum.educerti.order.service.OrderService
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
        return ResponseEntity.ok(orders.map { OrderResponse(it) })
    }

    // 특정 주문 조회
    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Long): ResponseEntity<OrderResponse> {
        val order = orderService.findById(id)
        return ResponseEntity.ok(OrderResponse(order))
    }


    // 주문 생성
    @PostMapping
    fun createOrder(@RequestBody request: OrderRequest): ResponseEntity<OrderResponse> {
        val savedOrder = orderService.save(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponse(savedOrder))
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
