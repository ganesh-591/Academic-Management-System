package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.BatchDetails;

public interface BatchDetailsService {

    BatchDetails addStudent(BatchDetails batchDetails);

    BatchDetails getBatchDetailById(Long batchDetailId);

    List<BatchDetails> getAllBatchDetails();

    BatchDetails updateBatchDetail(Long batchDetailId, BatchDetails batchDetails);

    void deleteBatchDetail(Long batchDetailId);
}


