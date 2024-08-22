package com.surveymanager.principalui.surveyUser.application;
import java.util.List;

import com.surveymanager.categories_catalog.domain.Categorie_catalog;
import com.surveymanager.principalui.surveyUser.domain.SurveyUserService;

public class FindCategoryUseCase {
    public final SurveyUserService surveyUserService;

    public FindCategoryUseCase(SurveyUserService surveyUserService) {
        this.surveyUserService = surveyUserService;
    }

    public List<Categorie_catalog> execute(){
       return surveyUserService.findAllCategories();
    }


}
