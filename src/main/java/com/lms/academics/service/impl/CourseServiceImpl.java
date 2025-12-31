package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.Course;
import com.lms.academics.repository.CourseRepository;
import com.lms.academics.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id: " + courseId));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long courseId, Course course) {
        Course existingCourse = getCourseById(courseId);

        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setDuration(course.getDuration());
        existingCourse.setToolsCovered(course.getToolsCovered());
        existingCourse.setCourseFee(course.getCourseFee());
        existingCourse.setCertificateProvided(course.getCertificateProvided());
        existingCourse.setStatus(course.getStatus());

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course = getCourseById(courseId);
        courseRepository.delete(course);
    }
}
