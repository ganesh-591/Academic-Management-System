package com.lms.academics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.academics.exception.ResourceNotFoundException;
import com.lms.academics.model.ExamOption;
import com.lms.academics.model.ExamQuestion;
import com.lms.academics.repository.ExamOptionRepository;
import com.lms.academics.repository.ExamQuestionRepository;
import com.lms.academics.service.ExamOptionService;

@Service
public class ExamOptionServiceImpl implements ExamOptionService {

    private final ExamOptionRepository optionRepository;
    private final ExamQuestionRepository questionRepository;

    public ExamOptionServiceImpl(
            ExamOptionRepository optionRepository,
            ExamQuestionRepository questionRepository) {
        this.optionRepository = optionRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public ExamOption addOption(ExamOption option) {

        if (option.getQuestion() == null ||
            option.getQuestion().getQuestionId() == null) {
            throw new IllegalArgumentException("QuestionId is required");
        }

        ExamQuestion question = questionRepository.findById(
                option.getQuestion().getQuestionId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found"));

        option.setQuestion(question);
        return optionRepository.save(option);
    }

    @Override
    public List<ExamOption> getOptionsByQuestion(Long questionId) {
        return optionRepository.findByQuestion_QuestionId(questionId);
    }

    @Override
    public void deleteOption(Long optionId) {
        optionRepository.deleteById(optionId);
    }
}
