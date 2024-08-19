package com.surveymanager.question.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.surveymanager.question.domain.entity.Question;
import com.surveymanager.question.domain.service.QuestionService;

public class QuestionRepository implements QuestionService {

    private Connection connection;

    public QuestionRepository() {
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
    public void createQuestion(Question question) {
        try {
            String query = """
                    INSERT INTO questions (created_at, updated_at, question_number, response_type, comment_question, question_text, chapter_id)
                    VALUES (?,?,?,?,?,?,?)
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, question.getCreated_at());
            ps.setTimestamp(2, question.getUpdated_at());
            ps.setString(3, question.getQuestion_number());
            ps.setString(4, question.getResponse_type());
            ps.setString(5, question.getComment_question());
            ps.setString(6, question.getQuestion_text());
            ps.setInt(7, question.getChapter_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuestion(Question question) {
        try {
            String query = """
                    UPDATE questions SET updated_at = ?, question_number = ?, response_type = ?, comment_question = ?,
                    question_text = ?, chapter_id = ? WHERE id = ?
                    WHERE id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, question.getUpdated_at());
            ps.setString(2, question.getQuestion_number());
            ps.setString(3, question.getResponse_type());
            ps.setString(4, question.getComment_question());
            ps.setString(5, question.getQuestion_text());
            ps.setInt(6, question.getChapter_id());
            ps.setInt(7, question.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteQuestion(int id) {
        try {
            String query = "DELETE FROM questions WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> findAllQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            String query = """
                    SELECT id, created_at, updated_at, question_number, response_type, comment_question, question_text, chapter_id
                    FROM questions ORDER BY id ASC
                    """;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Question question = new Question(
                        rs.getInt("id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getString("question_number"),
                        rs.getString("response_type"),
                        rs.getString("comment_question"),
                        rs.getString("question_text"),
                        rs.getInt("chapter_id"));
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public Question findByIdQuestion(int id) {
        Question question = new Question();
        try {
            String query = """
                    SELECT id, created_at, updated_at, question_number, response_type, comment_question, question_text, chapter_id
                    FROM questions WHERE id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                question.setId(rs.getInt("id"));
                question.setCreated_at(rs.getTimestamp("created_at"));
                question.setUpdated_at(rs.getTimestamp("updated_at"));
                question.setQuestion_number(rs.getString("question_number"));
                question.setResponse_type(rs.getString("response_type"));
                question.setComment_question(rs.getString("comment_question"));
                question.setQuestion_text(rs.getString("question_text"));
                question.setChapter_id(rs.getInt("chapter_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return question;
    }
}
