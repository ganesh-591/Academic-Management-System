package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.Module;

public interface ModuleService {

    Module createModule(Module module);

    Module getModuleById(Long moduleId);

    List<Module> getAllModules();

    Module updateModule(Long moduleId, Module module);

    void deleteModule(Long moduleId);
}
