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

import com.lms.academics.model.Topic;
import com.lms.academics.service.TopicService;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    // CREATE TOPIC
    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        Topic savedTopic = topicService.createTopic(topic);
        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }

    // GET TOPIC BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    // GET ALL TOPICS
    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    // UPDATE TOPIC
    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(
            @PathVariable Long id,
            @RequestBody Topic topic) {

        return ResponseEntity.ok(topicService.updateTopic(id, topic));
    }

    // DELETE TOPIC
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
