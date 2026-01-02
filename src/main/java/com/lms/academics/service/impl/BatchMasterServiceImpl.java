package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.BatchMaster;
import com.lms.academics.model.Course;
import com.lms.academics.repository.BatchMasterRepository;
import com.lms.academics.repository.CourseRepository;
import com.lms.academics.service.BatchMasterService;

@Service
public class BatchMasterServiceImpl implements BatchMasterService {

    private final BatchMasterRepository batchMasterRepository;
    private final CourseRepository courseRepository;

    public BatchMasterServiceImpl(BatchMasterRepository batchMasterRepository,
                                  CourseRepository courseRepository) {
        this.batchMasterRepository = batchMasterRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public BatchMaster create(Long courseId, BatchMaster batchMaster) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + courseId));

        // Ensure only ONE BatchMaster per Course
        batchMasterRepository.findByCourse_CourseId(courseId)
                .ifPresent(existing -> {
                    throw new IllegalStateException(
                            "BatchMaster already exists for course id: " + courseId);
                });

        batchMaster.setCourse(course);
        return batchMasterRepository.save(batchMaster);
    }

    @Override
    public BatchMaster getById(Long id) {
        return batchMasterRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "BatchMaster not found with id: " + id));
    }

    @Override
    public BatchMaster getByCourseId(Long courseId) {
        return batchMasterRepository.findByCourse_CourseId(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "BatchMaster not found for course id: " + courseId));
    }

    @Override
    public List<BatchMaster> getAll() {
        return batchMasterRepository.findAll();
    }

    @Override
    public BatchMaster patchUpdate(Long id, BatchMaster incoming) {

        BatchMaster existing = getById(id);

        if (incoming.getTotalBatches() != null)
            existing.setTotalBatches(incoming.getTotalBatches());

        if (incoming.getRunningBatches() != null)
            existing.setRunningBatches(incoming.getRunningBatches());

        if (incoming.getUpcomingBatches() != null)
            existing.setUpcomingBatches(incoming.getUpcomingBatches());

        if (incoming.getCompletedBatches() != null)
            existing.setCompletedBatches(incoming.getCompletedBatches());

        if (incoming.getTotalStudents() != null)
            existing.setTotalStudents(incoming.getTotalStudents());

        if (incoming.getParallelBatches() != null)
            existing.setParallelBatches(incoming.getParallelBatches());

        if (incoming.getRequiredTrainers() != null)
            existing.setRequiredTrainers(incoming.getRequiredTrainers());

        if (incoming.getStatus() != null)
            existing.setStatus(incoming.getStatus());

        return batchMasterRepository.save(existing);
    }

    @Override
    public void close(Long id) {
        BatchMaster batchMaster = getById(id);
        batchMaster.setStatus("CLOSED");
        batchMasterRepository.save(batchMaster);
    }
}
