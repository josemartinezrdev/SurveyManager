package com.surveymanager.survey.application;

import java.util.List;

import com.surveymanager.survey.domain.entity.Survey;
import com.surveymanager.survey.domain.service.SurveyService;

public class FindAllSurveyUseCase {
    private final SurveyService surveyService;

    public FindAllSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public List<Survey> execute() {
        return surveyService.findAllSurveys();
    }
}
