package com.aurum.educerti.applicationform.controller

import com.aurum.educerti.applicationform.dto.request.ApplicationFormRequest
import com.aurum.educerti.applicationform.dto.request.UpdateApplicationFormStatusRequest
import com.aurum.educerti.applicationform.dto.response.ApplicationFormResponse
import com.aurum.educerti.applicationform.service.ApplicationFormService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/application-forms")
class ApplicationFormApiController(
    private val applicationFormService: ApplicationFormService
) {
    // 전체 시험원서 조회
    @GetMapping
    fun getAllApplicationForms(): ResponseEntity<List<ApplicationFormResponse>> {
        val applicationForms = applicationFormService.findAll()
        val response = applicationForms.map {
            ApplicationFormResponse(it)
        }
        return ResponseEntity.ok(response)
    }

    // 특정 시험원서 조회
    @GetMapping("/{id}")
    fun getApplicationFormById(@PathVariable id: Long): ResponseEntity<ApplicationFormResponse> {
        val applicationForm = applicationFormService.findById(id)
        return ResponseEntity.ok(ApplicationFormResponse(applicationForm))
    }

    // 멤버번호로 시험원서 조회
    @GetMapping("/find-by-member-id/{memberId}")
    fun getApplicationFormsByMemberId(@PathVariable memberId: Long): ResponseEntity<List<ApplicationFormResponse>> {
        val applicationForms = applicationFormService.findByMemberId(memberId)
        return ResponseEntity.ok((applicationForms.map { ApplicationFormResponse(it) }))
    }

    // 시험원서 생성
    @PostMapping
    fun createApplicationForm(@RequestBody request: ApplicationFormRequest): ResponseEntity<ApplicationFormResponse> {
        val savedApplicationForm = applicationFormService.save(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(ApplicationFormResponse(savedApplicationForm))
    }

    // 시험원서 상태 업데이트
    @PutMapping("/{id}/status")
    fun updateApplicationFormStatus(
        @PathVariable id: Long,
        @RequestBody request: UpdateApplicationFormStatusRequest
    ): ResponseEntity<ApplicationFormResponse> {
        val savedApplicationForm = applicationFormService.updateStatus(id, request.status)
        return ResponseEntity.status(HttpStatus.CREATED).body(ApplicationFormResponse(savedApplicationForm))
    }


    // 시험원서 삭제
    @DeleteMapping("/{id}")
    fun deleteApplicationForm(@PathVariable id: Long): ResponseEntity<Void> {
        applicationFormService.delete(id)
        return ResponseEntity.noContent().build()
    }
}