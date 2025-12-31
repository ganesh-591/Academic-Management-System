package com.lms.academics.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.AttendanceSession;

public interface AttendanceSessionRepository
        extends JpaRepository<AttendanceSession, Long> {

    // All sessions of a batch
    List<AttendanceSession> findByBatch_BatchId(Long batchId);

    // Sessions of a batch on a specific date (MULTIPLE allowed)
    List<AttendanceSession> findByBatch_BatchIdAndSessionDate(
            Long batchId,
            LocalDate sessionDate
    );
}
