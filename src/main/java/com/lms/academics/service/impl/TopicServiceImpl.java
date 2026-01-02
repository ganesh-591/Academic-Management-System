package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.Module;
import com.lms.academics.model.Topic;
import com.lms.academics.repository.ModuleRepository;
import com.lms.academics.repository.TopicRepository;
import com.lms.academics.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final ModuleRepository moduleRepository;

    public TopicServiceImpl(TopicRepository topicRepository,
                            ModuleRepository moduleRepository) {
        this.topicRepository = topicRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    public Topic create(Long moduleId, Topic topic) {

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Module not found with id: " + moduleId));

        topic.setModule(module);

        if (topic.getStatus() == null) {
            topic.setStatus("ACTIVE");
        }

        return topicRepository.save(topic);
    }

    @Override
    public Topic getById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Topic not found with id: " + id));
    }

    @Override
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @Override
    public List<Topic> getByModuleId(Long moduleId) {
        return topicRepository.findByModule_ModuleId(moduleId);
    }

    @Override
    public Topic patchUpdate(Long id, Topic incoming) {

        Topic existing = getById(id);

        if (incoming.getTopicName() != null)
            existing.setTopicName(incoming.getTopicName());

        // ✅ FIXED: correct getter/setter
        if (incoming.getTopicDescription() != null)
            existing.setTopicDescription(incoming.getTopicDescription());

        if (incoming.getStatus() != null)
            existing.setStatus(incoming.getStatus());

        return topicRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        topicRepository.delete(getById(id));
    }
}
