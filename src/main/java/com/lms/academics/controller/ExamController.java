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

import com.lms.academics.model.Exam;
import com.lms.academics.service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService service;

    public ExamController(ExamService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Exam> create(@RequestBody Exam exam) {
        return new ResponseEntity<>(service.createExam(exam), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getExamById(id));
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<Exam>> getByBatch(@PathVariable Long batchId) {
        return ResponseEntity.ok(service.getExamsByBatch(batchId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> update(
            @PathVariable Long id,
            @RequestBody Exam exam) {
        return ResponseEntity.ok(service.updateExam(id, exam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}