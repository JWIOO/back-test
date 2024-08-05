package com.aurum.educerti.certificate.controller

import com.aurum.educerti.certificate.dto.request.CertificateRequest
import com.aurum.educerti.certificate.service.CertificateService
import com.aurum.educerti.exam.repository.ExamRepository
import com.aurum.educerti.member.repository.MemberRepository

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/certificates")
class CertificateApiController(
    private val certificateService: CertificateService,
    private val memberRepository: MemberRepository,
    private val examRepository: ExamRepository

) {

//    @GetMapping
//
//
//
//
//
//    @PostMapping





}
