package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.Topic;

public interface TopicService {

    Topic createTopic(Topic topic);

    Topic getTopicById(Long topicId);

    List<Topic> getAllTopics();

    Topic updateTopic(Long topicId, Topic topic);

    void deleteTopic(Long topicId);
}
