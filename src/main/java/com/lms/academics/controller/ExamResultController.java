package com.lms.academics.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.academics.model.ExamResult;
import com.lms.academics.service.ExamResultService;

@RestController
@RequestMapping("/api/exam-results")
public class ExamResultController {

    private final ExamResultService service;

    public ExamResultController(ExamResultService service) {
        this.service = service;
    }

    // POST RESULT
    @PostMapping
    public ResponseEntity<ExamResult> create(@RequestBody ExamResult result) {
        return new ResponseEntity<>(
                service.createResult(result),
                HttpStatus.CREATED);
    }

    // ✅ GET RESULT BY EXAM + STUDENT
    @GetMapping("/exam/{examId}/student/{studentId}")
    public ResponseEntity<ExamResult> getResult(
            @PathVariable Long examId,
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                service.getResultByExamAndStudent(examId, studentId));
    }

    // ✅ GET ALL RESULTS OF EXAM  ← THIS WAS MISSING OR MIS-MAPPED
    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamResult>> getResultsByExam(
            @PathVariable Long examId) {

        return ResponseEntity.ok(
                service.getResultsByExam(examId));
    }
}