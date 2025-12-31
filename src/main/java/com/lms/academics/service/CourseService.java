package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.Course;

public interface CourseService {

    Course createCourse(Course course);

    Course getCourseById(Long courseId);

    List<Course> getAllCourses();

    Course updateCourse(Long courseId, Course course);

    void deleteCourse(Long courseId);
}
