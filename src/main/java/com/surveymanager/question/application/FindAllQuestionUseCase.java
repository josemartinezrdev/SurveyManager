package com.surveymanager.question.application;

import java.util.List;

import com.surveymanager.question.domain.entity.Question;
import com.surveymanager.question.domain.service.QuestionService;

public class FindAllQuestionUseCase {
    private final QuestionService questionService;

    public FindAllQuestionUseCase(QuestionService questionService) {
        this.questionService = questionService;
    }

    public List<Question> execute() {
        return questionService.findAllQuestions();
    }
}
