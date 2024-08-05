package com.aurum.educerti.exam.domain

import com.aurum.educerti.common.domain.BaseEntity
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "exams")
class Exam(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "category", nullable = false)
    var category: String,

    @Column(name = "price", nullable = false)
    var price: BigDecimal,
) : BaseEntity()
