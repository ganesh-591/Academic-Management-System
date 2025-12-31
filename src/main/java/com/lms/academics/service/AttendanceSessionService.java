package com.lms.academics.service;

import java.time.LocalDate;
import java.util.List;

import com.lms.academics.model.AttendanceSession;

public interface AttendanceSessionService {

    AttendanceSession createSession(AttendanceSession session);

    AttendanceSession getSessionById(Long sessionId);

    List<AttendanceSession> getAllSessions();

    List<AttendanceSession> getSessionsByBatch(Long batchId);

    List<AttendanceSession> getSessionsByBatchAndDate(
            Long batchId, LocalDate date);

    AttendanceSession updateSession(Long sessionId, AttendanceSession session);

    void deleteSession(Long sessionId);
}
