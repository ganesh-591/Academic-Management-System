package com.lms.academics.controller;

import java.time.LocalDate;
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

import com.lms.academics.model.AttendanceSession;
import com.lms.academics.service.AttendanceSessionService;

@RestController
@RequestMapping("/api/attendance-session")
public class AttendanceSessionController {

    private final AttendanceSessionService service;

    public AttendanceSessionController(
            AttendanceSessionService service) {
        this.service = service;
    }

    // CREATE (Admin / Trainer)
    @PostMapping
    public ResponseEntity<AttendanceSession> create(
            @RequestBody AttendanceSession session) {

        return new ResponseEntity<>(
                service.createSession(session),
                HttpStatus.CREATED
        );
    }

    // GET ALL (Admin / Student)
    @GetMapping
    public ResponseEntity<List<AttendanceSession>> getAll() {
        return ResponseEntity.ok(service.getAllSessions());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceSession> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getSessionById(id));
    }

    // GET BY BATCH
    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<AttendanceSession>> getByBatch(
            @PathVariable Long batchId) {

        return ResponseEntity.ok(
                service.getSessionsByBatch(batchId));
    }

    // GET BY BATCH + DATE
    @GetMapping("/batch/{batchId}/date/{date}")
    public ResponseEntity<List<AttendanceSession>> getByBatchAndDate(
            @PathVariable Long batchId,
            @PathVariable LocalDate date) {

        return ResponseEntity.ok(
                service.getSessionsByBatchAndDate(batchId, date));
    }

    // UPDATE (Admin / Trainer)
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceSession> update(
            @PathVariable Long id,
            @RequestBody AttendanceSession session) {

        return ResponseEntity.ok(
                service.updateSession(id, session));
    }

    // DELETE (Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}
