package com.aurum.educerti.examsession.controller

import com.aurum.educerti.exam.dto.ExamSessionRequest
import com.aurum.educerti.examsession.domain.ExamSession
import com.aurum.educerti.examsession.service.ExamSessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/exam-sessions")
class ExamSessionApiController(private val examSessionService: ExamSessionService) {


    // 모든 시험 세션 조회
    @GetMapping
    fun getAllExamSessions(): ResponseEntity<List<ExamSession>> {
        val sessions = examSessionService.findAll()
        return ResponseEntity.ok(sessions)
    }

    // 특정 시험 세션 조회
    @GetMapping("/{id}")
    fun getExamSessionById(@PathVariable id: Long): ResponseEntity<ExamSession> {
        val session = examSessionService.findById(id)
        return ResponseEntity.ok(session)
    }

    // 시험 세션 제출
    @PostMapping
    fun createExamSession(@RequestBody request: ExamSessionRequest): ResponseEntity<ExamSession> {
        val savedSession = examSessionService.save(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSession)
    }


    // 첫 번째 파일 제출 (1차)
    @PutMapping("/{sessionId}/submit-first")
    fun submitFirstFile(@PathVariable sessionId: Long, @RequestParam filePath: String): ResponseEntity<ExamSession> {
        val session = examSessionService.submitFirstFile(sessionId, filePath)
        return ResponseEntity.ok(session)
    }

    // 두 번째 파일 제출 (2차)
    @PutMapping("/{sessionId}/submit-second")
    fun submitSecondFile(@PathVariable sessionId: Long, @RequestParam filePath: String): ResponseEntity<ExamSession> {
        val session = examSessionService.submitSecondFile(sessionId, filePath)
        return ResponseEntity.ok(session)
    }

    // 시험 세션 삭제
    @DeleteMapping("/{id}")
    fun deleteExamSession(@PathVariable id: Long): ResponseEntity<Void> {
        examSessionService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
