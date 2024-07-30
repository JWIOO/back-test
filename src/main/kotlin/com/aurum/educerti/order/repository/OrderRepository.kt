package com.aurum.educerti.order.repository


import com.aurum.educerti.order.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>
