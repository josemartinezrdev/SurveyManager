package com.surveymanager.response.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.surveymanager.response.domain.entity.Response;
import com.surveymanager.response.domain.service.ResponseService;

public class ResponseRepository implements ResponseService {

    private Connection connection;

    public ResponseRepository() {
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
    public void createResponse(Response response) {
        try {
            String query = """
                    INSERT INTO response_options (created_at, updated_at, optionvalue, typecomponenthtml, commentresponse, optiontext, categorycatalog_id, question_id, parentresponse_id)
                    VALUES (?,?,?,?,?,?,?,?,?)
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, response.getCreated_at());
            ps.setTimestamp(2, response.getUpdated_at());
            ps.setInt(3, response.getOptionValue());
            ps.setString(4, response.getTypeComponentHtml());
            ps.setString(5, response.getCommentResponse());
            ps.setString(6, response.getOptionText());
            ps.setInt(7, response.getCategoryCatalogId());
            ps.setInt(8, response.getQuestionId());
            ps.setInt(9, response.getParentResponseId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateResponse(Response response) {
        try {
            String query = """
                    UPDATE response_options SET created_at = ?, updated_at = ?, optionvalue = ?, typecomponenthtml = ?, commentresponse = ?, optiontext = ?, categorycatalog_id = ?, question_id = ?, parentresponse_id = ?
                    WHERE id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, response.getCreated_at());
            ps.setTimestamp(2, response.getUpdated_at());
            ps.setInt(3, response.getOptionValue());
            ps.setString(4, response.getTypeComponentHtml());
            ps.setString(5, response.getCommentResponse());
            ps.setString(6, response.getOptionText());
            ps.setInt(7, response.getCategoryCatalogId());
            ps.setInt(8, response.getQuestionId());
            ps.setInt(9, response.getParentResponseId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteResponse(int id) {
        try {
            String query = "DELETE FROM response_options WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Response> findAllResponses() {
        List<Response> responses = new ArrayList<>();
        try {
            String query = """
                    SELECT id, created_at, updated_at, optionvalue, typecomponenthtml, commentresponse, optiontext, categorycatalog_id, question_id, parentresponse_id
                    FROM response_options ORDER BY id ASC
                    """;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Response response = new Response(
                        rs.getInt("id"),
                        rs.getInt("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getTimestamp("optionvalue"),
                        rs.getString("typecomponenthtml"),
                        rs.getString("commentresponse"),
                        rs.getString("optiontext"),
                        rs.getInt("categorycatalog_id"),
                        rs.getInt("question_id"),
                        rs.getInt("parentresponse_id"));
                responses.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
    }

    @Override
    public Response findByIdResponse(int id) {
        Response response = new Response();
        try {
            String query = """
                    SELECT id, created_at, updated_at, optionvalue, typecomponenthtml, commentresponse, optiontext, categorycatalog_id, question_id, parentresponse_id
                    FROM response_options WHERE id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                response.setId(rs.getInt("id"));
                response.setOptionValue(rs.getInt("created_at"));
                response.setCreated_at(rs.getTimestamp("updated_at"));
                response.setUpdated_at(rs.getTimestamp("optionvalue"));
                response.setTypeComponentHtml(rs.getString("typecomponenthtml"));
                response.setCommentResponse(rs.getString("commentresponse"));
                response.setOptionText(rs.getString("optiontext"));
                response.setCategoryCatalogId(rs.getInt("categorycatalog_id"));
                response.setQuestionId(rs.getInt("question_id"));
                response.setParentResponseId(rs.getInt("parentresponse_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
