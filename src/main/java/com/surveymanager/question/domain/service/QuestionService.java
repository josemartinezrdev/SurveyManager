package com.surveymanager.question.domain.service;

import java.util.List;

import com.surveymanager.question.domain.entity.Question;

public interface QuestionService {
    public void createQuestion(Question question);

    public void updateQuestion(Question question);

    public void deleteQuestion(int id);

    public List<Question> findAllQuestions();

    public Question findByIdQuestion(int id);
}
