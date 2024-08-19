package com.surveymanager.survey.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.surveymanager.survey.domain.entity.Survey;
import com.surveymanager.survey.domain.service.SurveyService;

public class SurveyRepository implements SurveyService {

    private Connection connection;

    public SurveyRepository() {
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
    public void createSurvey(Survey survey) {
        try {
            String query = "INSERT INTO surveys (created_at, updated_at, description, name) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, survey.getCreated_at());
            ps.setTimestamp(2, survey.getUpdated_at());
            ps.setString(3, survey.getDescription());
            ps.setString(4, survey.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSurvey(Survey survey) {
        try {
            String query = "UPDATE surveys SET updated_at = ?, description = ?, name = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, survey.getUpdated_at());
            ps.setString(2, survey.getDescription());
            ps.setString(3, survey.getName());
            ps.setInt(4, survey.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSurvey(int id) {
        try {
            String query = "DELETE FROM surveys WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Survey> findAllSurveys() {
        List<Survey> surveys = new ArrayList<>();
        try {
            String query = """
                    SELECT id, created_at, updated_at, description, name FROM surveys ORDER BY id ASC
                    """;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Survey survey = new Survey(
                        rs.getInt("id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getString("description"),
                        rs.getString("name"));
                surveys.add(survey);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return surveys;
    }

    @Override
    public Survey findByIdSurvey(int id) {
        Survey survey = new Survey();
        try {
            String query = "SELECT id, created_at, updated_at, description, name FROM surveys WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                survey.setId(rs.getInt("id"));
                survey.setCreated_at(rs.getTimestamp("created_at"));
                survey.setUpdated_at(rs.getTimestamp("updated_at"));
                survey.setDescription(rs.getString("description"));
                survey.setName(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return survey;
    }

}
