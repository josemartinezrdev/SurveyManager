package com.surveymanager.survey.domain.service;

import java.util.List;

import com.surveymanager.survey.domain.entity.Survey;

public interface SurveyService {

    public void createSurvey(Survey survey);

    public void updateSurvey(Survey survey);

    public void deleteSurvey(int id);

    public List<Survey> findAllSurveys();

    public Survey findByIdSurvey(int id);

}
