package com.aurum.educerti.exam.controller

import com.aurum.educerti.exam.domain.Exam
import com.aurum.educerti.exam.dto.request.ExamRequest
import com.aurum.educerti.exam.service.ExamService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/exams")
class ExamApiController(private val examService: ExamService) {

    // 모든 시험 조회
    @GetMapping
    fun getAllExams(): ResponseEntity<List<Exam>> {
        val exams = examService.findAll()
        return ResponseEntity.ok(exams)
    }

    // 특정 시험 조회
    @GetMapping("/{id}")
    fun getExamById(@PathVariable id: Long): ResponseEntity<Exam> {
        val exam = examService.findById(id)
        return ResponseEntity.ok(exam)
    }


    // 시험 생성
    @PostMapping
    fun createExam(@RequestBody request: ExamRequest): ResponseEntity<Exam> {
        val savedExam = examService.save(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExam)
    }

    // 시험 삭제
    @DeleteMapping("/{id}")
    fun deleteExam(@PathVariable id: Long): ResponseEntity<Void> {
        examService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
