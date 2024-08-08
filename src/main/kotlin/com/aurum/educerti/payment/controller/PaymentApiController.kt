package com.aurum.educerti.payment.controller

import com.aurum.educerti.payment.domain.Payment
import com.aurum.educerti.payment.dto.PaymentRequest
import com.aurum.educerti.service.PaymentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payments")
class PaymentApiController(private val paymentService: PaymentService) {

    // 전체 결제 조회
    @GetMapping
    fun getAllPayments(): ResponseEntity<List<Payment>> {
        val payments = paymentService.findAll()
        return ResponseEntity.ok(payments)
    }

    // 특정 결제 조회
    @GetMapping("/{id}")
    fun getPaymentById(@PathVariable id: Long): ResponseEntity<Payment> {
        val payment = paymentService.findById(id)
        return ResponseEntity.ok(payment)
    }


    // 결제 생성
    @PostMapping
    fun createPayment(@RequestBody request: PaymentRequest): ResponseEntity<Payment> {
        val savedPayment = paymentService.save(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment)
    }


    // 결제 삭제
    @DeleteMapping("/{id}")
    fun deletePayment(@PathVariable id: Long): ResponseEntity<Void> {
        paymentService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
