package com.surveymanager.principalui.surveyUser.application;

import java.util.List;

import com.surveymanager.principalui.surveyUser.domain.SurveyUserService;
import com.surveymanager.subresponse.domain.Subresponse;

public class FindSubresponseUseCase {
    private final SurveyUserService surveyUserService;

    public FindSubresponseUseCase(SurveyUserService surveyUserService) {
        this.surveyUserService = surveyUserService;
    }

    public List<Subresponse> execute(int id) {
        return surveyUserService.findSubresponseByQuestion(id);
    }

    
}
