package com.lms.academics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.ExamQuestion;

public interface ExamQuestionRepository
        extends JpaRepository<ExamQuestion, Long> {

    // All questions of an exam
    List<ExamQuestion> findByExam_ExamId(Long examId);
}
