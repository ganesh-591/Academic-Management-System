package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.BatchMaster;
import com.lms.academics.model.Exam;
import com.lms.academics.repository.BatchMasterRepository;
import com.lms.academics.repository.ExamRepository;
import com.lms.academics.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final BatchMasterRepository batchRepository;

    public ExamServiceImpl(
            ExamRepository examRepository,
            BatchMasterRepository batchRepository) {
        this.examRepository = examRepository;
        this.batchRepository = batchRepository;
    }

    @Override
    public Exam createExam(Exam exam) {

        if (exam.getBatch() == null || exam.getBatch().getBatchId() == null) {
            throw new IllegalArgumentException("BatchId is required");
        }

        BatchMaster batch = batchRepository.findById(
                exam.getBatch().getBatchId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Batch not found"));

        exam.setBatch(batch);
        return examRepository.save(exam);
    }

    @Override
    public Exam getExamById(Long examId) {
        return examRepository.findById(examId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exam not found"));
    }

    @Override
    public List<Exam> getExamsByBatch(Long batchId) {
        return examRepository.findByBatch_BatchId(batchId);
    }

    @Override
    public Exam updateExam(Long examId, Exam updatedExam) {

        Exam existing = getExamById(examId);

        existing.setTitle(updatedExam.getTitle());
        existing.setExamType(updatedExam.getExamType());
        existing.setTotalMarks(updatedExam.getTotalMarks());
        existing.setPassMarks(updatedExam.getPassMarks());
        existing.setDuration(updatedExam.getDuration());
        existing.setExamDate(updatedExam.getExamDate());

        return examRepository.save(existing);
    }

    @Override
    public void deleteExam(Long examId) {
        examRepository.delete(getExamById(examId));
    }
}
