package com.lms.academics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.BatchMaster;

public interface BatchMasterRepository extends JpaRepository<BatchMaster, Long> {

    Optional<BatchMaster> findByCourse_CourseId(Long courseId);
}
