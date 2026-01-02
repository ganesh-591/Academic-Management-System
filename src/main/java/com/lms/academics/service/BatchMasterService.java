package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.BatchMaster;

public interface BatchMasterService {

    BatchMaster create(Long courseId, BatchMaster batchMaster);

    BatchMaster getById(Long id);

    BatchMaster getByCourseId(Long courseId);

    List<BatchMaster> getAll();

    // PATCH ONLY
    BatchMaster patchUpdate(Long id, BatchMaster batchMaster);

    void close(Long id);
}
