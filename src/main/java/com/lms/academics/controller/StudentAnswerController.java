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

import com.lms.academics.model.StudentAnswer;
import com.lms.academics.service.StudentAnswerService;

@RestController
@RequestMapping("/api/student-answers")
public class StudentAnswerController {

    private final StudentAnswerService service;

    public StudentAnswerController(StudentAnswerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudentAnswer> submit(
            @RequestBody StudentAnswer answer) {

        return new ResponseEntity<>(
                service.submitAnswer(answer),
                HttpStatus.CREATED);
    }

    @GetMapping("/exam/{examId}/student/{studentId}")
    public ResponseEntity<List<StudentAnswer>> getAnswers(
            @PathVariable Long examId,
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                service.getAnswers(examId, studentId));
    }
}
