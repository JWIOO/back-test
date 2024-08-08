package com.aurum.educerti.member.dto

import com.aurum.educerti.member.domain.Member
import org.springframework.data.domain.Page

data class MemberResponse(
    val totalCount: Long,
    val count: Int,
    val members: List<MemberDto>
) {
    data class MemberDto(
        val id: Long,
        val name: String,
        val password: String,
        val email: String,
        val address: String?,
        val phoneNumber: String?,
        val isAnonymous: Boolean,
    ) {
        constructor(member: Member) : this(
            id = member.id!!,
            name = member.name,
            password = member.password,
            email = member.email,
            address = member.address,
            phoneNumber = member.phoneNumber,
            isAnonymous = member.isAnonymous,
        )
    }

    companion object {
        fun of(members: Page<Member>): MemberResponse {
            val memberList = members.toList().map { MemberDto(it) }
            return MemberResponse(
                totalCount = members.totalElements,
                count = memberList.size,
                members = memberList
            )
        }
    }
}