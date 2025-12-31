package com.lms.academics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.AttendanceRecord;

public interface AttendanceRecordRepository
        extends JpaRepository<AttendanceRecord, Long> {

    // All records of a session
    List<AttendanceRecord> findByAttendanceSession_SessionId(Long sessionId);

    // All records of a student
    List<AttendanceRecord> findByStudentId(Long studentId);
}
