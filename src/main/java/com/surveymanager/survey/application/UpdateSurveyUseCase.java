package com.surveymanager.survey.application;

import com.surveymanager.survey.domain.entity.Survey;
import com.surveymanager.survey.domain.service.SurveyService;

public class UpdateSurveyUseCase {

    private final SurveyService surveyService;

    public UpdateSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public void execute(Survey survey) {
        surveyService.updateSurvey(survey);
    }

}
