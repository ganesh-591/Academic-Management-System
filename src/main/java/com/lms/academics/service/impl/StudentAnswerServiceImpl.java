package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.Exam;
import com.lms.academics.model.ExamQuestion;
import com.lms.academics.model.StudentAnswer;
import com.lms.academics.repository.ExamQuestionRepository;
import com.lms.academics.repository.ExamRepository;
import com.lms.academics.repository.StudentAnswerRepository;
import com.lms.academics.service.StudentAnswerService;

@Service
public class StudentAnswerServiceImpl implements StudentAnswerService {

    private final StudentAnswerRepository answerRepo;
    private final ExamRepository examRepo;
    private final ExamQuestionRepository questionRepo;

    public StudentAnswerServiceImpl(
            StudentAnswerRepository answerRepo,
            ExamRepository examRepo,
            ExamQuestionRepository questionRepo) {

        this.answerRepo = answerRepo;
        this.examRepo = examRepo;
        this.questionRepo = questionRepo;
    }

    @Override
    public StudentAnswer submitAnswer(StudentAnswer answer) {

        if (answer.getExam() == null || answer.getExam().getExamId() == null) {
            throw new IllegalArgumentException("ExamId is required");
        }

        if (answer.getQuestion() == null || answer.getQuestion().getQuestionId() == null) {
            throw new IllegalArgumentException("QuestionId is required");
        }

        Exam exam = examRepo.findById(answer.getExam().getExamId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exam not found"));

        ExamQuestion question = questionRepo.findById(answer.getQuestion().getQuestionId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found"));

        answer.setExam(exam);
        answer.setQuestion(question);

        return answerRepo.save(answer);
    }

    @Override
    public List<StudentAnswer> getAnswers(Long examId, Long studentId) {
        return answerRepo.findByExam_ExamIdAndStudentId(examId, studentId);
    }
}
