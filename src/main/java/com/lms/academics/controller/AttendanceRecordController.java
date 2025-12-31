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

import com.lms.academics.model.AttendanceRecord;
import com.lms.academics.service.AttendanceRecordService;

@RestController
@RequestMapping("/api/attendance-record")
public class AttendanceRecordController {

    private final AttendanceRecordService service;

    public AttendanceRecordController(AttendanceRecordService service) {
        this.service = service;
    }

    // CREATE (Admin / Trainer)
    @PostMapping
    public ResponseEntity<AttendanceRecord> create(
            @RequestBody AttendanceRecord record) {

        return new ResponseEntity<>(
                service.createRecord(record),
                HttpStatus.CREATED
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceRecord> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getRecordById(id));
    }

    // GET BY SESSION
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<AttendanceRecord>> getBySession(
            @PathVariable Long sessionId) {

        return ResponseEntity.ok(
                service.getRecordsBySession(sessionId));
    }

    // GET BY STUDENT
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceRecord>> getByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                service.getRecordsByStudent(studentId));
    }

    // UPDATE (Admin / Trainer)
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceRecord> update(
            @PathVariable Long id,
            @RequestBody AttendanceRecord record) {

        return ResponseEntity.ok(
                service.updateRecord(id, record));
    }

    // DELETE (Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
