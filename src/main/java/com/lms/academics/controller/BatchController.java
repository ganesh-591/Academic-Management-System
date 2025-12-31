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

import com.lms.academics.model.BatchMaster;
import com.lms.academics.service.BatchService;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    // CREATE BATCH
    @PostMapping
    public ResponseEntity<BatchMaster> createBatch(@RequestBody BatchMaster batch) {
        return new ResponseEntity<>(batchService.createBatch(batch), HttpStatus.CREATED);
    }


    // GET BATCH BY ID
    @GetMapping("/{id}")
    public ResponseEntity<BatchMaster> getBatchById(@PathVariable Long id) {
        return ResponseEntity.ok(batchService.getBatchById(id));
    }

    // GET ALL BATCHES
    @GetMapping
    public ResponseEntity<List<BatchMaster>> getAllBatches() {
        return ResponseEntity.ok(batchService.getAllBatches());
    }

    // UPDATE BATCH
    @PutMapping("/{id}")
    public ResponseEntity<BatchMaster> updateBatch(
            @PathVariable Long id,
            @RequestBody BatchMaster batch) {

        return ResponseEntity.ok(batchService.updateBatch(id, batch));
    }

    // DELETE BATCH
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
        return ResponseEntity.noContent().build();
    }
}
