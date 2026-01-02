package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.Course;
import com.lms.academics.model.Module;
import com.lms.academics.repository.CourseRepository;
import com.lms.academics.repository.ModuleRepository;
import com.lms.academics.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    public ModuleServiceImpl(ModuleRepository moduleRepository,
                             CourseRepository courseRepository) {
        this.moduleRepository = moduleRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Module create(Long courseId, Module module) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + courseId));

        module.setCourse(course);

        if (module.getStatus() == null) {
            module.setStatus("ACTIVE");
        }

        return moduleRepository.save(module);
    }

    @Override
    public Module getById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Module not found with id: " + id));
    }

    @Override
    public List<Module> getAll() {
        return moduleRepository.findAll();
    }

    @Override
    public List<Module> getByCourseId(Long courseId) {
        return moduleRepository.findByCourse_CourseId(courseId);
    }

    @Override
    public Module patchUpdate(Long id, Module incoming) {

        Module existing = getById(id);

        if (incoming.getModuleName() != null)
            existing.setModuleName(incoming.getModuleName());

        if (incoming.getDescription() != null)
            existing.setDescription(incoming.getDescription());

        if (incoming.getStatus() != null)
            existing.setStatus(incoming.getStatus());

        return moduleRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        moduleRepository.delete(getById(id));
    }
}
