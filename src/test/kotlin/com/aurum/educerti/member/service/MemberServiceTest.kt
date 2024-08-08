package com.aurum.educerti.member.service

import com.aurum.educerti.member.domain.Member
import com.aurum.educerti.member.dto.AddMemberRequest
import com.aurum.educerti.member.dto.UpdateMemberRequest
import com.aurum.educerti.member.repository.MemberRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockKExtension::class)
@ActiveProfiles("local")
class MemberServiceTest {
    @MockK
    private lateinit var memberRepository: MemberRepository

    private lateinit var memberService: MemberService

    @BeforeEach
    fun setUp() {
        memberService = MemberService(memberRepository)
    }

    @Test
    fun `Member 생성 테스트`() {
        // given
        val request = AddMemberRequest(
            name = "Test",
            password = "1234",
            email = "test@test.com",
            address = "테스트 주소",
            phoneNumber = "010-1234-1234",
            isAnonymous = false,
        )
        every { memberRepository.save(any()) } returns request.toEntity()

        // when
        val member = memberService.save(request.toEntity())

        // then
        assertNotNull(member)
    }

    @Test
    fun `Member 전체 조회 테스트`() {
        // given
        val pageNumber = 0
        val pageSize = 10
        val pageable = PageRequest.of(pageNumber, pageSize)

        val member1 = mockk<Member> {
            every { name } returns "test"
        }
        val members = listOf(
            member1,
            mockk<Member>(relaxed = true),
            mockk<Member>(relaxed = true),
        )

        every { memberRepository.findAllByDeletedAtIsNull(pageable) } returns PageImpl(members, pageable, members.size.toLong())

        // when
        val result = memberService.findAll(pageNumber, pageSize)

        // then
        assertEquals(members.size, result.content.size)
        assertEquals(members.first().name, member1.name)
    }

    @Test
    fun `Member 단건 조회 테스트`() {
        // given
        val id = 1L
        val member = mockk<Member>{
            every { name } returns "Test1"
            every { password } returns "1234"
            every { email } returns "test@test.com"
            every { address } returns "테스트 주소"
            every { phoneNumber } returns "010-1234-1234"
            every { isAnonymous } returns false
        }
        every { memberRepository.findById(id) } returns Optional.of(member)

        // when
        val result = memberService.findById(id)

        // then
        assertEquals(member.name, result.name)
    }

    @Test
    fun `Member 단건 조회 테스트 - 존재하지 않는 member`() {
        // given
        val id = 1L
        every { memberRepository.findById(id) } returns Optional.empty()

        // when

        // then
        assertThrows<IllegalArgumentException> {
            memberService.findById(id)
        }
    }

    @Test
    fun `Member 수정 테스트`() {
        // given
        val id = 1L
        val request = UpdateMemberRequest(
            name = "Update Test",
            username = "변경 테스트",
            password = "1234",
            email = "test@test.com",
            address = "테스트 주소",
            phoneNumber = "010-1234-1234",
            isAnonymous = false,
        )
        val member = mockk<Member> {
            every { name } returns "Test"
            every { password } returns "1234"
            every { email } returns "test@test.com"
            every { address } returns "테스트 주소"
            every { phoneNumber } returns "010-1234-1234"
            every { isAnonymous } returns false
        }
        every { memberRepository.findById(id) } returns Optional.of(member)
        every { memberRepository.save(any()) } returns member

        // when
        val result = memberService.update(id, request)

        // then
        assertEquals(request.name, result.name)
    }

    @Test
    fun `Member 수정 테스트 - 존재하지 않는 member`() {
        // given
        val id = 1L
        val request = UpdateMemberRequest(
            name = "Update Test",
            username = "변경 테스트",
            password = "1234",
            email = "test@test.com",
            address = "테스트 주소",
            phoneNumber = "010-1234-1234",
            isAnonymous = false,
        )
        every { memberRepository.findById(id) } returns Optional.empty()

        // when

        // then
        assertThrows<IllegalArgumentException> {
            memberService.update(id, request)
        }
    }

    @Test
    fun `Member 삭제 테스트`() {
        // given
        val id = 1L
        val member = mockk<Member> {
            every { name } returns "Test"
            every { password } returns "1234"
            every { email } returns "test@test.com"
            every { address } returns "테스트 주소"
            every { phoneNumber } returns "010-1234-1234"
            every { isAnonymous } returns false
            every { deletedAt } returns null

        }
        every { memberRepository.findById(id) } returns Optional.of(member)
        every { memberRepository.save(any()) } returns member

        // when
        memberService.delete(id)

        // then
        assertNotNull(member.deletedAt)
    }

    @Test
    fun `Member 삭제 테스트 - 존재하지 않는 member`() {
        // given
        val id = 1L
        every { memberRepository.findById(id) } returns Optional.empty()

        // when

        // then
        assertThrows<IllegalArgumentException> {
            memberService.delete(id)
        }
    }
}