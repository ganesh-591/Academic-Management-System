package com.lms.academics.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.academics.model.Module;
import com.lms.academics.service.ModuleService;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    // CREATE MODULE
    @PostMapping
    public ResponseEntity<Module> createModule(@RequestBody Module module) {
        Module savedModule = moduleService.createModule(module);
        return new ResponseEntity<>(savedModule, HttpStatus.CREATED);
    }

    // GET MODULE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(moduleService.getModuleById(id));
    }

    // GET ALL MODULES
    @GetMapping
    public ResponseEntity<List<Module>> getAllModules() {
        return ResponseEntity.ok(moduleService.getAllModules());
    }

    // UPDATE MODULE
    @PutMapping("/{id}")
    public ResponseEntity<Module> updateModule(
            @PathVariable Long id,
            @RequestBody Module module) {

        return ResponseEntity.ok(moduleService.updateModule(id, module));
    }

    // DELETE MODULE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }
}
