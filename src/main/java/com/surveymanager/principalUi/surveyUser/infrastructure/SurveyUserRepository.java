package com.surveymanager.principalui.surveyUser.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.surveymanager.chapter.domain.entity.Chapter;
import com.surveymanager.principalui.surveyUser.domain.SurveyUser;
import com.surveymanager.principalui.surveyUser.domain.SurveyUserService;
import com.surveymanager.question.domain.entity.Question;
import com.surveymanager.response.domain.entity.Response;
import com.surveymanager.subresponse.domain.Subresponse;

public class SurveyUserRepository implements SurveyUserService {

    private Connection connection;

    public SurveyUserRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Chapter> findChapterBySurvey(int id) {
        List<Chapter> chapters = new ArrayList<>();
        try {
            String query = """
                    SELECT id, created_at, updated_at, chapter_number, chapter_title, survey_id FROM
                    chapter WHERE survey_id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chapter chapter = new Chapter(
                        rs.getInt("id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getString("chapter_number"),
                        rs.getString("chapter_title"),
                        rs.getInt("survey_id"));
                chapters.add(chapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chapters;
    }

    @Override
    public List<Question> findQuestionByCategory(int idChapter, int idCategory) {
        List<Question> questions = new ArrayList<>();
        try {
            String query = """
                    SELECT
                        distinct(que.id),
                        que.created_at,
                        que.updated_at,
                        que.question_number,
                        que.response_type,
                        que.comment_question,
                        que.question_text,
                        que.chapter_id,
                        cat.id AS categoria
                    FROM questions que
                    INNER JOIN response_options rep ON que.id = rep.question_id
                    INNER JOIN categories_catalog cat ON rep.categorycatalog_id = cat.id
                    WHERE que.chapter_id = ? AND cat.id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idChapter);
            ps.setInt(2, idCategory);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setCreated_at(rs.getTimestamp("created_at"));
                question.setUpdated_at(rs.getTimestamp("updated_at"));
                question.setQuestion_number(rs.getString("question_number"));
                question.setResponse_type(rs.getString("response_type"));
                question.setComment_question(rs.getString("comment_question"));
                question.setQuestion_text(rs.getString("question_text"));
                question.setChapter_id(rs.getInt("chapter_id"));
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public List<Response> findResponseByQuestion(int id) {
        List<Response> responses = new ArrayList<>();
        try {
            String query = """
                    SELECT
                        id,
                        created_at,
                        updated_at,
                        option_value,
                        typecomponenthtml,
                        comment_response,
                        option_text,
                        categorycatalog_id,
                        question_id,
                        parentresponse_id
                    FROM response_options
                    WHERE question_id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Response response = new Response();
                response.setId(rs.getInt("id"));
                responses.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
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
