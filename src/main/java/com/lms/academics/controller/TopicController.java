package com.lms.academics.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.academics.model.Topic;
import com.lms.academics.service.TopicService;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    // CREATE TOPIC UNDER MODULE
    @PostMapping("/module/{moduleId}")
    public ResponseEntity<Topic> create(
            @PathVariable Long moduleId,
            @RequestBody Topic topic) {

        return new ResponseEntity<>(
                service.create(moduleId, topic),
                HttpStatus.CREATED
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Topic>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET BY MODULE
    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<Topic>> getByModule(
            @PathVariable Long moduleId) {

        return ResponseEntity.ok(service.getByModuleId(moduleId));
    }

    // PATCH ONLY
    @PatchMapping("/{id}")
    public ResponseEntity<Topic> patchUpdate(
            @PathVariable Long id,
            @RequestBody Topic topic) {

        return ResponseEntity.ok(service.patchUpdate(id, topic));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
