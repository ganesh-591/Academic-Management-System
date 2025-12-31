package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.ExamAttempt;

public interface ExamAttemptService {

    ExamAttempt startAttempt(ExamAttempt attempt);

    ExamAttempt getAttemptById(Long attemptId);

    List<ExamAttempt> getAttemptsByExam(Long examId);

    List<ExamAttempt> getAttemptsByStudent(Long studentId);

    ExamAttempt submitAttempt(Long attemptId);
}
