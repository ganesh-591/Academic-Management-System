package com.lms.academics.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.academics.model.BatchMaster;
import com.lms.academics.service.BatchMasterService;

@RestController
@RequestMapping("/api/batch-masters")
public class BatchMasterController {

    private final BatchMasterService service;

    public BatchMasterController(BatchMasterService service) {
        this.service = service;
    }

    // CREATE BATCH MASTER FOR COURSE
    @PostMapping("/course/{courseId}")
    public ResponseEntity<BatchMaster> create(
            @PathVariable Long courseId,
            @RequestBody BatchMaster batchMaster) {

        return new ResponseEntity<>(
                service.create(courseId, batchMaster),
                HttpStatus.CREATED
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<BatchMaster> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // GET BY COURSE
    @GetMapping("/course/{courseId}")
    public ResponseEntity<BatchMaster> getByCourse(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(service.getByCourseId(courseId));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<BatchMaster>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // PATCH ONLY
    @PatchMapping("/{id}")
    public ResponseEntity<BatchMaster> patchUpdate(
            @PathVariable Long id,
            @RequestBody BatchMaster batchMaster) {

        return ResponseEntity.ok(service.patchUpdate(id, batchMaster));
    }

    // CLOSE (NOT DELETE)
    @PatchMapping("/{id}/close")
    public ResponseEntity<Void> close(@PathVariable Long id) {
        service.close(id);
        return ResponseEntity.noContent().build();
    }
}
