package com.lms.academics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByModuleModuleId(Long moduleId);
}
