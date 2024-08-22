package com.surveymanager.principalui.surveyUser.infrastructure;

import java.sql.Connection;
import java.util.List;

import com.surveymanager.categories_catalog.domain.Categorie_catalog;
import com.surveymanager.chapter.domain.entity.Chapter;
import com.surveymanager.principalui.surveyUser.domain.SurveyUser;
import com.surveymanager.principalui.surveyUser.domain.SurveyUserService;
import com.surveymanager.question.domain.entity.Question;
import com.surveymanager.response.domain.entity.Response;
import com.surveymanager.subresponse.domain.Subresponse;

public class SurveyUserRepository implements SurveyUserService {

    private Connection connection;

    // public SurveyUserRepository() {
    //     try {
    //         Properties pros = new
            

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    @Override
    public List<Chapter> findChapterBySurvey(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findChapterBySurvey'");
    }

    @Override
    public List<Categorie_catalog> findAllCategories() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllCategories'");
    }

    @Override
    public List<Question> findQuestionByCategory(int idSurvey, int idCategory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findQuestionByCategory'");
    }

    @Override
    public List<Response> findResponseByQuestion(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findResponseByQuestion'");
    }

    @Override
    public List<Subresponse> findSubresponseByQuestion(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSubresponseByQuestion'");
    }

    @Override
    public void createSurveyUser(SurveyUser surveyUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSurveyUser'");
    }

}
