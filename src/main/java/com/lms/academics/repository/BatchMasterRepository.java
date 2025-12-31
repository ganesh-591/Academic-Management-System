package com.lms.academics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.BatchMaster;

public interface BatchMasterRepository extends JpaRepository<BatchMaster, Long> {

    List<BatchMaster> findByCourseCourseId(Long courseId);

    List<BatchMaster> findByTrainerId(Long trainerId);
}
