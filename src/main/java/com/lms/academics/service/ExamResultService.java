package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.ExamResult;

public interface ExamResultService {

    // CREATE
    ExamResult createResult(ExamResult result);

    // GET RESULT OF ONE STUDENT IN EXAM
    ExamResult getResultByExamAndStudent(Long examId, Long studentId);

    // GET ALL RESULTS OF AN EXAM
    List<ExamResult> getResultsByExam(Long examId);
}
