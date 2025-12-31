package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.AttendanceRecord;
import com.lms.academics.model.AttendanceSession;
import com.lms.academics.repository.AttendanceRecordRepository;
import com.lms.academics.repository.AttendanceSessionRepository;
import com.lms.academics.service.AttendanceRecordService;

@Service
public class AttendanceRecordServiceImpl
        implements AttendanceRecordService {

    private final AttendanceRecordRepository recordRepository;
    private final AttendanceSessionRepository sessionRepository;

    public AttendanceRecordServiceImpl(
            AttendanceRecordRepository recordRepository,
            AttendanceSessionRepository sessionRepository) {

        this.recordRepository = recordRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public AttendanceRecord createRecord(AttendanceRecord record) {

        if (record.getAttendanceSession() == null ||
            record.getAttendanceSession().getSessionId() == null) {
            throw new IllegalArgumentException("SessionId is required");
        }

        Long sessionId = record.getAttendanceSession().getSessionId();

        AttendanceSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance session not found: " + sessionId));

        record.setAttendanceSession(session);
        return recordRepository.save(record);
    }

    @Override
    public AttendanceRecord getRecordById(Long recordId) {
        return recordRepository.findById(recordId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance record not found: " + recordId));
    }

    @Override
    public List<AttendanceRecord> getRecordsBySession(Long sessionId) {
        return recordRepository.findByAttendanceSession_SessionId(sessionId);
    }

    @Override
    public List<AttendanceRecord> getRecordsByStudent(Long studentId) {
        return recordRepository.findByStudentId(studentId);
    }

    @Override
    public AttendanceRecord updateRecord(
            Long recordId, AttendanceRecord updated) {

        AttendanceRecord existing = getRecordById(recordId);

        existing.setStatus(updated.getStatus());
        return recordRepository.save(existing);
    }

    @Override
    public void deleteRecord(Long recordId) {
        AttendanceRecord record = getRecordById(recordId);
        recordRepository.delete(record);
    }
}
