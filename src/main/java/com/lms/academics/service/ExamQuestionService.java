package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.ExamQuestion;

public interface ExamQuestionService {

    ExamQuestion addQuestion(ExamQuestion question);

    ExamQuestion getQuestionById(Long questionId);

    List<ExamQuestion> getQuestionsByExam(Long examId);

    ExamQuestion updateQuestion(Long questionId, ExamQuestion question);

    void deleteQuestion(Long questionId);
}