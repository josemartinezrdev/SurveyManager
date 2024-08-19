package com.surveymanager.question.application;

import com.surveymanager.question.domain.entity.Question;
import com.surveymanager.question.domain.service.QuestionService;

public class CreateQuestionUseCase {
    private final QuestionService questionService;

    public CreateQuestionUseCase(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void execute(Question question) {
        questionService.createQuestion(question);
    }
}
