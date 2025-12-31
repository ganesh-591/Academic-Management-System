package com.lms.academics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    // All exams for a batch
    List<Exam> findByBatch_BatchId(Long batchId);
}
