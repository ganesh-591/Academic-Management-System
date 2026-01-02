package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.Module;

public interface ModuleService {

    Module create(Long courseId, Module module);

    Module getById(Long id);

    List<Module> getAll();

    List<Module> getByCourseId(Long courseId);

    // PATCH ONLY
    Module patchUpdate(Long id, Module module);

    void delete(Long id);
}
