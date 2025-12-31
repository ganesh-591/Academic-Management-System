package com.lms.academics.service;

import java.util.List;

import com.lms.academics.model.ExamOption;

public interface ExamOptionService {

    ExamOption addOption(ExamOption option);

    List<ExamOption> getOptionsByQuestion(Long questionId);

    void deleteOption(Long optionId);
}