package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.Exam;
import com.lms.academics.model.ExamQuestion;
import com.lms.academics.repository.ExamQuestionRepository;
import com.lms.academics.repository.ExamRepository;
import com.lms.academics.service.ExamQuestionService;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {

    private final ExamQuestionRepository questionRepository;
    private final ExamRepository examRepository;

    public ExamQuestionServiceImpl(
            ExamQuestionRepository questionRepository,
            ExamRepository examRepository) {
        this.questionRepository = questionRepository;
        this.examRepository = examRepository;
    }

    @Override
    public ExamQuestion addQuestion(ExamQuestion question) {

        if (question.getExam() == null || question.getExam().getExamId() == null) {
            throw new IllegalArgumentException("ExamId is required");
        }

        Exam exam = examRepository.findById(
                question.getExam().getExamId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exam not found"));

        question.setExam(exam);
        return questionRepository.save(question);
    }

    @Override
    public ExamQuestion getQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found"));
    }

    @Override
    public List<ExamQuestion> getQuestionsByExam(Long examId) {
        return questionRepository.findByExam_ExamId(examId);
    }

    @Override
    public ExamQuestion updateQuestion(Long questionId, ExamQuestion updated) {

        ExamQuestion existing = getQuestionById(questionId);

        existing.setQuestionText(updated.getQuestionText());
        existing.setQuestionType(updated.getQuestionType());
        existing.setMarks(updated.getMarks());

        return questionRepository.save(existing);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionRepository.delete(getQuestionById(questionId));
    }
}
