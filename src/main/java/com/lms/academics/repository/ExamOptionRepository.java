package com.lms.academics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.ExamOption;

public interface ExamOptionRepository
        extends JpaRepository<ExamOption, Long> {

    // All options for a question
    List<ExamOption> findByQuestion_QuestionId(Long questionId);
}
