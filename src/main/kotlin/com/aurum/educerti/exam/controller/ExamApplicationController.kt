package com.aurum.educerti.exam.controller



import com.aurum.educerti.exam.dto.ExamApplicationResponse
import com.aurum.educerti.exam.service.ExamApplicationService
import com.aurum.educerti.exam.dto.StartExamRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ExamApplicationController(private val examApplicationService: ExamApplicationService) {

    @PostMapping("/api/exam-applications/start")
    fun startExam(@RequestBody request: StartExamRequest): ExamApplicationResponse {
        val application = examApplicationService.startExam(request.examOrderId, request.examCode)
        return ExamApplicationResponse(application.id ?: throw IllegalStateException("Application ID cannot be null"))
    }

    @GetMapping("/api/exam-applications")
    fun listExamApplications(): List<ExamApplicationResponse> {
        val applications = examApplicationService.getAllExamApplications()
        return applications.map { ExamApplicationResponse(it.id ?: throw IllegalStateException("Application ID cannot be null")) }
    }
}
