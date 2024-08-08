package com.aurum.educerti.refund.service


import com.aurum.educerti.refund.domain.Refund
import com.aurum.educerti.refund.repository.RefundRepository
import org.springframework.stereotype.Service

@Service
class RefundService(private val refundRepository: RefundRepository) {

    fun createRefund(refund: Refund): Long {
        return refundRepository.save(refund).id ?: throw IllegalStateException("Refund ID should not be null")
    }

    fun getRefundById(refundId: Long): Refund? {
        return refundRepository.findById(refundId).orElse(null)
    }

    // 기타 필요한 메서드들 추가
}
