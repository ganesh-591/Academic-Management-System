package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.StudentAnswer;

public interface StudentAnswerService {

    StudentAnswer submitAnswer(StudentAnswer answer);

    List<StudentAnswer> getAnswers(Long examId, Long studentId);
}
