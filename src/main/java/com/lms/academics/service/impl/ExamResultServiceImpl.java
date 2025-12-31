package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.Exam;
import com.lms.academics.model.ExamResult;
import com.lms.academics.repository.ExamRepository;
import com.lms.academics.repository.ExamResultRepository;
import com.lms.academics.service.ExamResultService;

@Service
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepository resultRepository;
    private final ExamRepository examRepository;

    public ExamResultServiceImpl(
            ExamResultRepository resultRepository,
            ExamRepository examRepository) {
        this.resultRepository = resultRepository;
        this.examRepository = examRepository;
    }

    @Override
    public ExamResult createResult(ExamResult result) {

        if (result.getExam() == null || result.getExam().getExamId() == null) {
            throw new IllegalArgumentException("ExamId is required");
        }

        Exam exam = examRepository.findById(
                result.getExam().getExamId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exam not found"));

        result.setExam(exam);
        return resultRepository.save(result);
    }

    @Override
    public ExamResult getResultByExamAndStudent(Long examId, Long studentId) {
        return resultRepository
                .findByExam_ExamIdAndStudentId(examId, studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Result not found"));
    }

    @Override
    public List<ExamResult> getResultsByExam(Long examId) {
        return resultRepository.findByExam_ExamId(examId);
    }
}
