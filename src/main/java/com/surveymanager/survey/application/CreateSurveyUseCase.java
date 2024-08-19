package com.surveymanager.survey.application;

import com.surveymanager.survey.domain.entity.Survey;
import com.surveymanager.survey.domain.service.SurveyService;

public class CreateSurveyUseCase {
    private final SurveyService surveyService;

    public CreateSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public void execute(Survey survey) {
        surveyService.createSurvey(survey);
    }
}
