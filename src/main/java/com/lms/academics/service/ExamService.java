package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.Exam;

public interface ExamService {

    Exam createExam(Exam exam);

    Exam getExamById(Long examId);

    List<Exam> getExamsByBatch(Long batchId);

    Exam updateExam(Long examId, Exam exam);

    void deleteExam(Long examId);
}