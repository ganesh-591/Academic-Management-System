package com.lms.academics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.BatchDetails;

public interface BatchDetailsRepository extends JpaRepository<BatchDetails, Long> {

    List<BatchDetails> findByBatchBatchId(Long batchId);

    List<BatchDetails> findByStudentId(Long studentId);
}
