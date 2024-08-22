package com.surveymanager.principalui.surveyUser.domain;

import java.util.List;

import com.surveymanager.categories_catalog.domain.Categorie_catalog;
import com.surveymanager.chapter.domain.entity.Chapter;
import com.surveymanager.question.domain.entity.Question;
import com.surveymanager.response.domain.entity.Response;
import com.surveymanager.subresponse.domain.Subresponse;

public interface SurveyUserService {
    public List<Chapter> findChapterBySurvey(int id);
    public List<Categorie_catalog> findAllCategories();
    public List<Question> findQuestionByCategory(int id);
    public List<Response> findResponseByQuestion(int id);
    public List<Subresponse> findSubresponseByQuestion(int id);
    public void createSurveyUser(SurveyUser surveyUser);


}
