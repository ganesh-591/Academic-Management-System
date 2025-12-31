package com.lms.academics.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.academics.model.ExamOption;
import com.lms.academics.service.ExamOptionService;

@RestController
@RequestMapping("/api/exam-options")
public class ExamOptionController {

    private final ExamOptionService service;

    public ExamOptionController(ExamOptionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExamOption> add(@RequestBody ExamOption option) {
        return new ResponseEntity<>(service.addOption(option), HttpStatus.CREATED);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<ExamOption>> getByQuestion(
            @PathVariable Long questionId) {
        return ResponseEntity.ok(service.getOptionsByQuestion(questionId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteOption(id);
        return ResponseEntity.noContent().build();
    }
}
