package com.lms.academics.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.Exam;
import com.lms.academics.model.ExamAttempt;
import com.lms.academics.repository.ExamAttemptRepository;
import com.lms.academics.repository.ExamRepository;
import com.lms.academics.service.ExamAttemptService;

@Service
public class ExamAttemptServiceImpl implements ExamAttemptService {

    private final ExamAttemptRepository attemptRepo;
    private final ExamRepository examRepo;

    public ExamAttemptServiceImpl(
            ExamAttemptRepository attemptRepo,
            ExamRepository examRepo) {
        this.attemptRepo = attemptRepo;
        this.examRepo = examRepo;
    }

    @Override
    public ExamAttempt startAttempt(ExamAttempt attempt) {

        if (attempt.getExam() == null || attempt.getExam().getExamId() == null) {
            throw new IllegalArgumentException("ExamId is required");
        }

        Exam exam = examRepo.findById(attempt.getExam().getExamId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exam not found"));

        attempt.setExam(exam);
        return attemptRepo.save(attempt);
    }

    @Override
    public ExamAttempt getAttemptById(Long attemptId) {
        return attemptRepo.findById(attemptId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attempt not found"));
    }

    @Override
    public List<ExamAttempt> getAttemptsByExam(Long examId) {
        return attemptRepo.findByExam_ExamId(examId);
    }

    @Override
    public List<ExamAttempt> getAttemptsByStudent(Long studentId) {
        return attemptRepo.findByStudentId(studentId);
    }

    @Override
    public ExamAttempt submitAttempt(Long attemptId) {

        ExamAttempt attempt = getAttemptById(attemptId);

        attempt.setStatus("SUBMITTED");
        attempt.setEndTime(LocalDateTime.now());

        return attemptRepo.save(attempt);
    }
}
