package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.Course;
import com.lms.academics.repository.CourseRepository;
import com.lms.academics.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Course create(Course course) {
        return repository.save(course);
    }

    @Override
    public Course getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + id));
    }

    @Override
    public List<Course> getAll() {
        return repository.findAll();
    }

    @Override
    public Course patchUpdate(Long id, Course incoming) {

        Course existing = getById(id);

        if (incoming.getCourseName() != null)
            existing.setCourseName(incoming.getCourseName());

        if (incoming.getDescription() != null)
            existing.setDescription(incoming.getDescription());

        if (incoming.getDuration() != null)
            existing.setDuration(incoming.getDuration());

        if (incoming.getToolsCovered() != null)
            existing.setToolsCovered(incoming.getToolsCovered());

        if (incoming.getCourseFee() != null)
            existing.setCourseFee(incoming.getCourseFee());

        if (incoming.getCertificateProvided() != null)
            existing.setCertificateProvided(incoming.getCertificateProvided());

        if (incoming.getStatus() != null)
            existing.setStatus(incoming.getStatus());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
