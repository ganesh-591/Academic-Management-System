package com.lms.academics.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.academics.model.ExamQuestion;
import com.lms.academics.service.ExamQuestionService;

@RestController
@RequestMapping("/api/exam-questions")
public class ExamQuestionController {

    private final ExamQuestionService service;

    public ExamQuestionController(ExamQuestionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExamQuestion> add(@RequestBody ExamQuestion question) {
        return new ResponseEntity<>(service.addQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamQuestion>> getByExam(@PathVariable Long examId) {
        return ResponseEntity.ok(service.getQuestionsByExam(examId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamQuestion> update(
            @PathVariable Long id,
            @RequestBody ExamQuestion question) {
        return ResponseEntity.ok(service.updateQuestion(id, question));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
