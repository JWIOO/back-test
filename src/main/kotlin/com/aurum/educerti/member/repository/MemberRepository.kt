package com.aurum.educerti.member.repository


import com.aurum.educerti.member.domain.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>{
    fun findAllByDeletedAtIsNull(pageable: Pageable): Page<Member>

}
