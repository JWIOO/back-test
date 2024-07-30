package com.aurum.educerti.refund.repository

import com.aurum.educerti.refund.domain.Refund
import org.springframework.data.jpa.repository.JpaRepository

interface RefundRepository : JpaRepository<Refund, Long> {
}