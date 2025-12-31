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

import com.lms.academics.model.BatchDetails;
import com.lms.academics.service.BatchDetailsService;

@RestController
@RequestMapping("/api/batch-details")
public class BatchDetailsController {

    private final BatchDetailsService batchDetailsService;

    public BatchDetailsController(BatchDetailsService batchDetailsService) {
        this.batchDetailsService = batchDetailsService;
    }

    // ➕ ADD STUDENT
    @PostMapping
    public ResponseEntity<BatchDetails> addStudent(@RequestBody BatchDetails batchDetails) {
        return new ResponseEntity<>(
                batchDetailsService.addStudent(batchDetails),
                HttpStatus.CREATED
        );
    }

    // 📄 GET ALL
    @GetMapping
    public ResponseEntity<List<BatchDetails>> getAllBatchDetails() {
        return ResponseEntity.ok(batchDetailsService.getAllBatchDetails());
    }

    // ✏️ PUT (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<BatchDetails> updateBatchDetail(
            @PathVariable Long id,
            @RequestBody BatchDetails batchDetails) {

        return ResponseEntity.ok(
                batchDetailsService.updateBatchDetail(id, batchDetails)
        );
    }

    // ❌ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatchDetail(@PathVariable Long id) {
        batchDetailsService.deleteBatchDetail(id);
        return ResponseEntity.noContent().build();
    }
}
