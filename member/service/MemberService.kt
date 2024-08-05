package com.aurum.educerti.member.service

import com.aurum.educerti.member.domain.Member
import com.aurum.educerti.member.dto.UpdateMemberRequest
import com.aurum.educerti.member.repository.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(private val memberRepository: MemberRepository) {

    // 생성 (Member 라는 엔티티의 형태로)
    fun save(member: Member): Member {
        return memberRepository.save(member)
    }

    // 단건 조회
    fun findById(memberId: Long): Member {
        return memberRepository.findById(memberId)
            .orElseThrow { IllegalArgumentException("Unexpected member") }
    }

    // 전체 조회 (pagination)
    fun findAll(pageNumber: Int, pageSize: Int): Page<Member> {
        return memberRepository.findAllByDeletedAtIsNull(PageRequest.of(pageNumber, pageSize))
    }

    // 업데이트
    @Transactional
    fun update(id: Long, request: UpdateMemberRequest): Member {
        val member = findById(id)
        member.update(
            name = request.name,
            username = request.username,
            password = request.password,
            email = request.email,
            address = request.address,
            phoneNumber = request.phoneNumber,
            isAnonymous = request.isAnonymous,
        )
        return member
    }

    // 삭제
    @Transactional
    fun delete(id: Long) {
        val member = findById(id)
        member.delete() // 실제 Data 삭제는 하지 않고, deleted_at 필드 업데이트
    }
}
