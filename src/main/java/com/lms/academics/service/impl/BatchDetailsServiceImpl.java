package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.BatchDetails;
import com.lms.academics.repository.BatchDetailsRepository;
import com.lms.academics.service.BatchDetailsService;

@Service
public class BatchDetailsServiceImpl implements BatchDetailsService {

    private final BatchDetailsRepository batchDetailsRepository;

    public BatchDetailsServiceImpl(BatchDetailsRepository batchDetailsRepository) {
        this.batchDetailsRepository = batchDetailsRepository;
    }

    @Override
    public BatchDetails addStudent(BatchDetails batchDetails) {
        return batchDetailsRepository.save(batchDetails);
    }

    @Override
    public BatchDetails getBatchDetailById(Long batchDetailId) {
        return batchDetailsRepository.findById(batchDetailId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Batch detail not found with id: " + batchDetailId));
    }

    @Override
    public List<BatchDetails> getAllBatchDetails() {
        return batchDetailsRepository.findAll();
    }

    // ✅ PUT (UPDATE)
    @Override
    public BatchDetails updateBatchDetail(Long batchDetailId, BatchDetails batchDetails) {
        BatchDetails existing = getBatchDetailById(batchDetailId);

        existing.setStudentId(batchDetails.getStudentId());
        existing.setJoinedDate(batchDetails.getJoinedDate());
        existing.setStatus(batchDetails.getStatus());

        // 🔒 SAFE PARENT UPDATE
        if (batchDetails.getBatch() != null) {
            existing.setBatch(batchDetails.getBatch());
        }

        return batchDetailsRepository.save(existing);
    }

    // ✅ DELETE
    @Override
    public void deleteBatchDetail(Long batchDetailId) {
        BatchDetails batchDetails = getBatchDetailById(batchDetailId);
        batchDetailsRepository.delete(batchDetails);
    }
}
