package com.lms.academics.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.AttendanceSession;
import com.lms.academics.model.BatchMaster;
import com.lms.academics.repository.AttendanceSessionRepository;
import com.lms.academics.repository.BatchMasterRepository;
import com.lms.academics.service.AttendanceSessionService;

@Service
public class AttendanceSessionServiceImpl
        implements AttendanceSessionService {

    private final AttendanceSessionRepository sessionRepository;
    private final BatchMasterRepository batchRepository;

    public AttendanceSessionServiceImpl(
            AttendanceSessionRepository sessionRepository,
            BatchMasterRepository batchRepository) {
        this.sessionRepository = sessionRepository;
        this.batchRepository = batchRepository;
    }

    @Override
    public AttendanceSession createSession(AttendanceSession session) {

        if (session.getBatch() == null ||
            session.getBatch().getBatchId() == null) {
            throw new IllegalArgumentException("BatchId is required");
        }

        Long batchId = session.getBatch().getBatchId();

        BatchMaster batch = batchRepository.findById(batchId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Batch not found with id: " + batchId));

        session.setBatch(batch);
        return sessionRepository.save(session);
    }

    @Override
    public AttendanceSession getSessionById(Long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance session not found: " + sessionId));
    }

    @Override
    public List<AttendanceSession> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public List<AttendanceSession> getSessionsByBatch(Long batchId) {
        return sessionRepository.findByBatch_BatchId(batchId);
    }

    @Override
    public List<AttendanceSession> getSessionsByBatchAndDate(
            Long batchId, LocalDate date) {

        return sessionRepository
                .findByBatch_BatchIdAndSessionDate(batchId, date);
    }

    @Override
    public AttendanceSession updateSession(
            Long sessionId, AttendanceSession updated) {

        AttendanceSession existing = getSessionById(sessionId);

        existing.setSessionDate(updated.getSessionDate());
        existing.setTopic(updated.getTopic());
        existing.setCreatedBy(updated.getCreatedBy());

        return sessionRepository.save(existing);
    }

    @Override
    public void deleteSession(Long sessionId) {
        AttendanceSession session = getSessionById(sessionId);
        sessionRepository.delete(session);
    }
}
