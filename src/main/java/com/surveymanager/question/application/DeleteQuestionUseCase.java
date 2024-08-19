package com.surveymanager.question.application;

import com.surveymanager.question.domain.service.QuestionService;

public class DeleteQuestionUseCase {
    private final QuestionService questionService;

    public DeleteQuestionUseCase(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void execute(int id) {
        questionService.deleteQuestion(id);
    }
}
