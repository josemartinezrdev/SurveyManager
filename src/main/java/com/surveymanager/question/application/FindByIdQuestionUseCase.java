package com.surveymanager.question.application;

import com.surveymanager.question.domain.entity.Question;
import com.surveymanager.question.domain.service.QuestionService;

public class FindByIdQuestionUseCase {
    private final QuestionService questionService;

    public FindByIdQuestionUseCase(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Question execute(int id) {
        return questionService.findByIdQuestion(id);
    }
}
