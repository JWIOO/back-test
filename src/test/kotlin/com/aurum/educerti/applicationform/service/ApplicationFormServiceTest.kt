package com.aurum.educerti.applicationform.service

import com.aurum.educerti.applicationform.domain.ApplicationForm
import com.aurum.educerti.applicationform.domain.ApplicationFormStatus
import com.aurum.educerti.applicationform.dto.request.ApplicationFormRequest
import com.aurum.educerti.applicationform.repository.ApplicationFormRepository
import com.aurum.educerti.exam.domain.Exam
import com.aurum.educerti.exam.repository.ExamRepository
import com.aurum.educerti.member.domain.Member
import com.aurum.educerti.member.repository.MemberRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import kotlin.test.assertEquals
import java.util.Optional

@ExtendWith(MockKExtension::class)
class ApplicationFormServiceTest {

    @MockK
    private lateinit var applicationFormRepository: ApplicationFormRepository

    @MockK
    private lateinit var memberRepository: MemberRepository

    @MockK
    private lateinit var examRepository: ExamRepository

    private lateinit var applicationFormService: ApplicationFormService

    @BeforeEach
    fun setUp() {
        applicationFormService = ApplicationFormService(applicationFormRepository, memberRepository, examRepository)
    }


    //🔸🔸🔸🔸🔸save 테스트🔸🔸🔸🔸🔸
    @Test
    @DisplayName("##ApplicationForm 생성 테스트")
    fun `ApplicationForm 생성 테스트`() {
        // Given
        val exam = Exam(id = 1L, name = "Test Exam", category = "Category", price = BigDecimal(100))
        val member = Member(id = 1L, name = "Test Member", password = "password", email = "test@test.com", address = "Test Address", phoneNumber = "010-1234-5678", isAnonymous = false)
        val request = ApplicationFormRequest(examId = 1L, memberId = 1L)

        every { examRepository.findById(1L) } returns Optional.of(exam)
        every { memberRepository.findById(1L) } returns Optional.of(member)
        every { applicationFormRepository.save(any<ApplicationForm>()) } returns ApplicationForm(id = 1L, exam = exam, member = member)

        // When
        val result = applicationFormService.save(request)

        // Then
        verify { applicationFormRepository.save(any<ApplicationForm>()) }
        assertEquals(1L, result.id)
        assertEquals(exam, result.exam)
        assertEquals(member, result.member)
    }

    @Test
    fun `ApplicationForm 생성 테스트 - Exam이 존재하지 않을 때`() {
        // Given
        val request = ApplicationFormRequest(examId = 1L, memberId = 1L)

        every { examRepository.findById(1L) } returns Optional.empty()

        // When & Then
        assertThrows<IllegalArgumentException> {
            applicationFormService.save(request)
        }
    }

    @Test
    fun `ApplicationForm 생성 테스트 - Member가 존재하지 않을 때`() {
        // Given
        val exam = Exam(id = 1L, name = "Test Exam", category = "Category", price = BigDecimal(100))
        val request = ApplicationFormRequest(examId = 1L, memberId = 1L)

        every { examRepository.findById(1L) } returns Optional.of(exam)
        every { memberRepository.findById(1L) } returns Optional.empty()

        // When & Then
        assertThrows<IllegalArgumentException> {
            applicationFormService.save(request)
        }
    }


    //🔸🔸🔸🔸🔸findAll 테스트🔸🔸🔸🔸🔸
    @Test
    @DisplayName("ApplicationForm 전체 조회 테스트")
    fun `ApplicationForm 전체 조회 테스트`() {
        // Given
        val exam1 = Exam(id = 1L, name = "Test Exam 1", category = "Category 1", price = BigDecimal(100))
        val member1 = Member(id = 1L, name = "Test Member 1", password = "password", email = "test1@test.com", address = "Test Address 1", phoneNumber = "010-1234-5678", isAnonymous = false)
        val applicationForm1 = ApplicationForm(id = 1L, exam = exam1, member = member1)

        val exam2 = Exam(id = 2L, name = "Test Exam 2", category = "Category 2", price = BigDecimal(200))
        val member2 = Member(id = 2L, name = "Test Member 2", password = "password", email = "test2@test.com", address = "Test Address 2", phoneNumber = "010-9876-5432", isAnonymous = false)
        val applicationForm2 = ApplicationForm(id = 2L, exam = exam2, member = member2)

        every { applicationFormRepository.findAll() } returns listOf(applicationForm1, applicationForm2)

        // When
        val result = applicationFormService.findAll()

        // Then
        verify { applicationFormRepository.findAll() }
        assertEquals(2, result.size)
        assertEquals(1L, result[0].id)
        assertEquals(2L, result[1].id)
    }


    //🔸🔸🔸🔸🔸findById 테스트🔸🔸🔸🔸🔸
    @Test
    @DisplayName("ApplicationForm ID로 조회 테스트")
    fun `ApplicationForm ID로 조회 테스트`() {
        // Given
        val exam = Exam(id = 1L, name = "Test Exam", category = "Category", price = BigDecimal(100))
        val member = Member(id = 1L, name = "Test Member", password = "password", email = "test@test.com", address = "Test Address", phoneNumber = "010-1234-5678", isAnonymous = false)
        val applicationForm = ApplicationForm(id = 1L, exam = exam, member = member)

        every { applicationFormRepository.findById(1L) } returns Optional.of(applicationForm)

        // When
        val result = applicationFormService.findById(1L)

        // Then
        verify { applicationFormRepository.findById(1L) }
        assertEquals(1L, result.id)
        assertEquals(applicationForm, result)
    }

    @Test
    @DisplayName("ApplicationForm ID로 조회 테스트 - 존재하지 않는 경우")
    fun `ApplicationForm ID로 조회 테스트 - 존재하지 않는 경우`() {
        // Given
        every { applicationFormRepository.findById(1L) } returns Optional.empty()

        // When & Then
        assertThrows<IllegalArgumentException> {
            applicationFormService.findById(1L)
        }
    }

    //🔸🔸🔸🔸🔸findByMemberId 테스트🔸🔸🔸🔸🔸
    @Test
    @DisplayName("Member ID로 ApplicationForm 조회 테스트")
    fun `Member ID로 ApplicationForm 조회 테스트`() {
        // Given
        val exam1 = Exam(id = 1L, name = "Test Exam 1", category = "Category 1", price = BigDecimal(100))
        val member = Member(id = 1L, name = "Test Member", password = "password", email = "test@test.com", address = "Test Address", phoneNumber = "010-1234-5678", isAnonymous = false)
        val applicationForm1 = ApplicationForm(id = 1L, exam = exam1, member = member)

        val exam2 = Exam(id = 2L, name = "Test Exam 2", category = "Category 2", price = BigDecimal(200))
        val applicationForm2 = ApplicationForm(id = 2L, exam = exam2, member = member)

        every { applicationFormRepository.findByMemberId(1L) } returns listOf(applicationForm1, applicationForm2)

        // When
        val result = applicationFormService.findByMemberId(1L)

        // Then
        verify { applicationFormRepository.findByMemberId(1L) }
        assertEquals(2, result.size)
        assertEquals(1L, result[0].member.id)
        assertEquals(1L, result[1].member.id)
    }

    @Test
    @DisplayName("Member ID로 ApplicationForm 조회 테스트 - Member가 존재하지 않을 때")
    fun `Member ID로 ApplicationForm 조회 테스트 - Member가 존재하지 않을 때`() {
        // Given
        every { applicationFormRepository.findByMemberId(1L) } returns emptyList()

        // When
        val result = applicationFormService.findByMemberId(1L)

        // Then
        verify { applicationFormRepository.findByMemberId(1L) }
        assertTrue(result.isEmpty())
    }


    //🔸🔸🔸🔸🔸updateStatus 테스트🔸🔸🔸🔸🔸
    @Test
    fun `ApplicationForm 상태 업데이트 테스트`() {
        // Given
        val exam = Exam(id = 1L, name = "Test Exam", category = "Category", price = BigDecimal(100))
        val member = Member(id = 1L, name = "Test Member", password = "password", email = "test@test.com", address = "Test Address", phoneNumber = "010-1234-5678", isAnonymous = false)
        val applicationForm = ApplicationForm(id = 1L, exam = exam, member = member)

        every { applicationFormRepository.findById(1L) } returns Optional.of(applicationForm)
        every { applicationFormRepository.save(any<ApplicationForm>()) } answers { firstArg() }

        // When
        val result = applicationFormService.updateStatus(1L, ApplicationFormStatus.APPROVED)

        // Then
        verify { applicationFormRepository.findById(1L) }
        verify { applicationFormRepository.save(any<ApplicationForm>()) }
        assertEquals(ApplicationFormStatus.APPROVED, result.status)
    }

    @Test
    fun `ApplicationForm 상태 업데이트 테스트 - ApplicationForm이 존재하지 않을 때`() {
        // Given
        every { applicationFormRepository.findById(1L) } returns Optional.empty()

        // When & Then
        assertThrows<IllegalArgumentException> {
            applicationFormService.updateStatus(1L, ApplicationFormStatus.APPROVED)
        }
    }

    @Test
    fun `ApplicationForm 상태 업데이트 테스트 - REJECTED 상태로 업데이트`() {
        // Given
        // 초기화 안 하면 밑에 applicationForm 실행이 안 됨
        val exam = Exam(id = 1L, name = "Test Exam", category = "Category", price = BigDecimal(100))
        val member = Member(id = 1L, name = "Test Member", password = "password", email = "test@test.com", address = "Test Address", phoneNumber = "010-1234-5678", isAnonymous = false)
        val applicationForm = ApplicationForm(id = 1L, exam = exam, member = member)

        every { applicationFormRepository.findById(1L) } returns Optional.of(applicationForm)
        every { applicationFormRepository.save(any<ApplicationForm>()) } answers { firstArg() }

        // When
        val result = applicationFormService.updateStatus(1L, ApplicationFormStatus.REJECTED)

        // Then
        verify { applicationFormRepository.save(any<ApplicationForm>()) }
        assertEquals(ApplicationFormStatus.REJECTED, result.status)
    }



    //🔸🔸🔸🔸🔸delete 테스트🔸🔸🔸🔸🔸
    @Test
    fun `ApplicationForm 삭제 테스트`() {
        // Given
        val exam = Exam(id = 1L, name = "Test Exam", category = "Category", price = BigDecimal(100))
        val member = Member(id = 1L, name = "Test Member", password = "password", email = "test@test.com", address = "Test Address", phoneNumber = "010-1234-5678", isAnonymous = false)
        val applicationForm = ApplicationForm(id = 1L, exam = exam, member = member)

        every { applicationFormRepository.findById(1L) } returns Optional.of(applicationForm)
        every { applicationFormRepository.deleteById(1L) } returns Unit

        // When
        applicationFormService.delete(1L)

        // Then
        verify { applicationFormRepository.deleteById(1L) }
    }

    @Test
    fun `ApplicationForm 삭제 테스트 - 존재하지 않는 ApplicationForm`() {
        // Given
        every { applicationFormRepository.findById(1L) } returns Optional.empty()

        // When
        val exception = assertThrows<IllegalArgumentException> {
            applicationFormService.delete(1L)
        }

        // Then
        assertEquals("ApplicationForm not found: 1", exception.message)
    }

}
