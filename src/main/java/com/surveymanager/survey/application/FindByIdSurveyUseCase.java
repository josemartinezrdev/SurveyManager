package com.surveymanager.survey.application;

import com.surveymanager.survey.domain.entity.Survey;
import com.surveymanager.survey.domain.service.SurveyService;

public class FindByIdSurveyUseCase {
    private final SurveyService surveyService;

    public FindByIdSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public Survey execute(int id) {
        return surveyService.findByIdSurvey(id);
    }
}
