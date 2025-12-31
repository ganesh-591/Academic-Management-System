package com.lms.academics.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.ExamResult;

public interface ExamResultRepository
        extends JpaRepository<ExamResult, Long> {

    Optional<ExamResult> findByExam_ExamIdAndStudentId(
            Long examId, Long studentId);

    List<ExamResult> findByExam_ExamId(Long examId);
}
