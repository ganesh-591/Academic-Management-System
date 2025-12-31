package com.lms.academics.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.academics.model.ExamAttempt;
import com.lms.academics.service.ExamAttemptService;

@RestController
@RequestMapping("/api/exam-attempts")
public class ExamAttemptController {

    private final ExamAttemptService service;

    public ExamAttemptController(ExamAttemptService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExamAttempt> start(
            @RequestBody ExamAttempt attempt) {

        return new ResponseEntity<>(
                service.startAttempt(attempt),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}/submit")
    public ResponseEntity<ExamAttempt> submit(@PathVariable Long id) {
        return ResponseEntity.ok(service.submitAttempt(id));
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamAttempt>> byExam(
            @PathVariable Long examId) {
        return ResponseEntity.ok(service.getAttemptsByExam(examId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamAttempt>> byStudent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(service.getAttemptsByStudent(studentId));
    }
}
