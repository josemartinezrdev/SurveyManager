package com.surveymanager.question.application;

import com.surveymanager.question.domain.entity.Question;
import com.surveymanager.question.domain.service.QuestionService;

public class UpdateQuestionUseCase {
    private final QuestionService questionService;

    public UpdateQuestionUseCase(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void execute(Question question) {
        questionService.updateQuestion(question);
    }
}
