package com.aurum.educerti.payment.repository

import com.aurum.educerti.payment.domain.Payment
import org.springframework.data.jpa.repository.JpaRepository



interface PaymentRepository : JpaRepository<Payment, Long>
