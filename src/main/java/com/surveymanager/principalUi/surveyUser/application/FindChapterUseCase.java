package com.surveymanager.principalui.surveyUser.application;

import java.util.List;

import com.surveymanager.chapter.domain.entity.Chapter;
import com.surveymanager.principalui.surveyUser.domain.SurveyUserService;

public class FindChapterUseCase {
    private final SurveyUserService surveyUserService;

    public FindChapterUseCase(SurveyUserService surveyUserService) {
        this.surveyUserService = surveyUserService;
    }

    public  List<Chapter> execute(int id){
       return surveyUserService.findChapterBySurvey(id);
    }


}
