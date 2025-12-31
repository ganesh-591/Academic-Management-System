package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.AttendanceRecord;

public interface AttendanceRecordService {

    AttendanceRecord createRecord(AttendanceRecord record);

    AttendanceRecord getRecordById(Long recordId);

    List<AttendanceRecord> getRecordsBySession(Long sessionId);

    List<AttendanceRecord> getRecordsByStudent(Long studentId);

    AttendanceRecord updateRecord(Long recordId, AttendanceRecord record);

    void deleteRecord(Long recordId);
}
