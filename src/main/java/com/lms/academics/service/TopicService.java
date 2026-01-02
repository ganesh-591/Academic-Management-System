package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.Topic;

public interface TopicService {

    Topic create(Long moduleId, Topic topic);

    Topic getById(Long id);

    List<Topic> getAll();

    List<Topic> getByModuleId(Long moduleId);

    // PATCH ONLY
    Topic patchUpdate(Long id, Topic topic);

    void delete(Long id);
}
