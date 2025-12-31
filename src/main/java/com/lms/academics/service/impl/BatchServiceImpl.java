package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.BatchMaster;
import com.lms.academics.repository.BatchMasterRepository;
import com.lms.academics.service.BatchService;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchMasterRepository batchMasterRepository;

    public BatchServiceImpl(BatchMasterRepository batchMasterRepository) {
        this.batchMasterRepository = batchMasterRepository;
    }

    @Override
    public BatchMaster createBatch(BatchMaster batch) {
        return batchMasterRepository.save(batch);
    }

    @Override
    public BatchMaster getBatchById(Long batchId) {
        return batchMasterRepository.findById(batchId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Batch not found with id: " + batchId));
    }

    @Override
    public List<BatchMaster> getAllBatches() {
        return batchMasterRepository.findAll();
    }

    @Override
    public BatchMaster updateBatch(Long batchId, BatchMaster batch) {
        BatchMaster existing = getBatchById(batchId);

        existing.setBatchName(batch.getBatchName());
        existing.setTrainerId(batch.getTrainerId());
        existing.setStartDate(batch.getStartDate());
        existing.setEndDate(batch.getEndDate());
        existing.setDays(batch.getDays());
        existing.setTimeSlot(batch.getTimeSlot());
        existing.setStatus(batch.getStatus());

        // 🔒 SAFE PARENT UPDATE
        if (batch.getCourse() != null) {
            existing.setCourse(batch.getCourse());
        }

        return batchMasterRepository.save(existing);
    }

    @Override
    public void deleteBatch(Long batchId) {
        BatchMaster batch = getBatchById(batchId);
        batchMasterRepository.delete(batch);
    }
}
