package com.surveymanager.principalui.surveyUser.application;

import java.util.List;

import com.surveymanager.principalui.surveyUser.domain.SurveyUserService;
import com.surveymanager.response.domain.entity.Response;

public class FindResponseUseCase {
    private final SurveyUserService surveyUserService;

    public FindResponseUseCase(SurveyUserService surveyUserService) {
        this.surveyUserService = surveyUserService;
    }

    public List<Response> execute(int id){
        return surveyUserService.findResponseByQuestion(id);
    }

}
