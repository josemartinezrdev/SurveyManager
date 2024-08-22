package com.surveymanager.principalui.surveyUser.application;

import java.util.List;

import com.surveymanager.principalui.surveyUser.domain.SurveyUserService;
import com.surveymanager.question.domain.entity.Question;

public class FindQuestionUseCase {
    public final SurveyUserService SurveyUserService;

    public FindQuestionUseCase(SurveyUserService SurveyUserService) {
        this.SurveyUserService = SurveyUserService;
    }

    public List<Question> execute(int idSurvey, int idCategory) {
        return SurveyUserService.findQuestionByCategory(idSurvey, idCategory);
    }

}
