package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.Topic;
import com.lms.academics.repository.TopicRepository;
import com.lms.academics.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Topic getTopicById(Long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic not found with id: " + topicId));
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic updateTopic(Long topicId, Topic topic) {
        Topic existing = getTopicById(topicId);

        existing.setTopicName(topic.getTopicName());
        existing.setDescription(topic.getDescription());
        existing.setTopicDuration(topic.getTopicDuration());

        // 🔒 SAFE PARENT UPDATE
        if (topic.getModule() != null) {
            existing.setModule(topic.getModule());
        }

        return topicRepository.save(existing);
    }

    @Override
    public void deleteTopic(Long topicId) {
        Topic topic = getTopicById(topicId);
        topicRepository.delete(topic);
    }
}
