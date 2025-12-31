package com.lms.academics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.ExamAttempt;

public interface ExamAttemptRepository
        extends JpaRepository<ExamAttempt, Long> {

    // ✅ CORRECT
    List<ExamAttempt> findByExam_ExamId(Long examId);

    List<ExamAttempt> findByStudentId(Long studentId);
}
