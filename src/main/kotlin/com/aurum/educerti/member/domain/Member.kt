package com.aurum.educerti.member.domain

import com.aurum.educerti.common.domain.BaseEntity
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "members")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "username", nullable = false)
    var username: String, //닉네임

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "address")
    var address: String?,

    @Column(name = "phone_number")
    var phoneNumber: String?,

    @Column(name = "is_anonymous", nullable = false)
    var isAnonymous: Boolean = false,

    var deletedAt: ZonedDateTime? = null,
) : BaseEntity() {

    fun update(name: String, username: String, password: String, email: String, address: String?, phoneNumber: String?, isAnonymous: Boolean) {
        this.name = name
        this.username = username
        this.password = password
        this.email = email
        this.address = address
        this.phoneNumber = phoneNumber
        this.isAnonymous = isAnonymous
    }

    fun delete() {
        this.deletedAt = ZonedDateTime.now()
    }
}
