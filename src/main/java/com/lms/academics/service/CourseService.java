package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.Course;

public interface CourseService {

    Course create(Course course);

    Course getById(Long id);

    List<Course> getAll();

    // PATCH ONLY
    Course patchUpdate(Long id, Course course);

    void delete(Long id);
}
