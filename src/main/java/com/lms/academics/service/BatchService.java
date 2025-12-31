package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.BatchMaster;

public interface BatchService {

    BatchMaster createBatch(BatchMaster batch);

    BatchMaster getBatchById(Long batchId);

    List<BatchMaster> getAllBatches();

    BatchMaster updateBatch(Long batchId, BatchMaster batch);

    void deleteBatch(Long batchId);
}
