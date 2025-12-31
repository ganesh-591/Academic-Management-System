package com.lms.academics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findByCourseCourseId(Long courseId);
}
