package com.aurum.educerti.member.controller

import com.aurum.educerti.member.domain.Member
import com.aurum.educerti.member.dto.AddMemberRequest
import com.aurum.educerti.member.dto.MemberResponse
import com.aurum.educerti.member.dto.UpdateMemberRequest
import com.aurum.educerti.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/members")
class MemberApiController(
    private val memberService: MemberService
) {
    @GetMapping
    fun findAllMembers(
        @RequestParam pageNumber: Int?,
        @RequestParam pageSize: Int?,
    ): ResponseEntity<MemberResponse> {
        val members = memberService.findAll(
            pageNumber = pageNumber ?: DEFAULT_ALL_MEMBERS_PAGE_NUMBER,
            pageSize = pageSize ?: DEFAULT_ALL_MEMBERS_PAGE_SIZE,
        )

        return ResponseEntity.ok(MemberResponse.of(members))
    }

    @GetMapping("/{id}")
    fun findMember(@PathVariable id: Long): ResponseEntity<MemberResponse.MemberDto> {
        val member = memberService.findById(id)
        return ResponseEntity.ok(MemberResponse.MemberDto(member))
    }

    @PostMapping
    fun addMember(@RequestBody request: List<AddMemberRequest>): ResponseEntity<List<Member>> {
        val savedMember = memberService.saveAll(request.map { it.toEntity() })
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember)
    }

    @PutMapping("/{id}")
    fun updateMember(
        @PathVariable id: Long,
        @RequestBody request: UpdateMemberRequest/**/
    ): ResponseEntity<Member> {
        val updatedMember = memberService.update(id, request)
        return ResponseEntity.ok(updatedMember)
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long): ResponseEntity<Member> {
        memberService.delete(id)
        return ResponseEntity.ok().build()
    }

    companion object {
        private const val DEFAULT_ALL_MEMBERS_PAGE_NUMBER = 0
        private const val DEFAULT_ALL_MEMBERS_PAGE_SIZE = 10
    }
}