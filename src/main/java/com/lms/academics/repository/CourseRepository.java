package com.lms.academics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
