package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.Module;
import com.lms.academics.repository.ModuleRepository;
import com.lms.academics.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    public ModuleServiceImpl(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Override
    public Module createModule(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public com.lms.academics.model.Module getModuleById(Long moduleId) {
        return moduleRepository.findById(moduleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Module not found with id: " + moduleId));
    }

    @Override
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    @Override
    public Module updateModule(Long moduleId, Module module) {
        Module existingModule = getModuleById(moduleId);

        existingModule.setModuleName(module.getModuleName());
        existingModule.setDescription(module.getDescription());
        existingModule.setModuleDuration(module.getModuleDuration());

        // ✅ SAFE UPDATE (VERY IMPORTANT)
        if (module.getCourse() != null) {
            existingModule.setCourse(module.getCourse());
        }

        return moduleRepository.save(existingModule);
    }

    @Override
    public void deleteModule(Long moduleId) {
        Module module = getModuleById(moduleId);
        moduleRepository.delete(module);
    }
}
