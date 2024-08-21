package com.surveymanager.subresponse.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;

import com.surveymanager.subresponse.domain.Subresponse;
import com.surveymanager.subresponse.domain.SubresponseService;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubresponseRepository implements SubresponseService {

    private Connection connection;

    public SubresponseRepository() {
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
    public void createSubresponse(Subresponse subresponse) {
        try {
            String query = """
                    INSERT INTO subresponse_options(
                        subresponse_number,
                        created_at,
                        updated_at,
                        component_html,
                        subresponse_text,
                        responseoptions_id)
                    VALUES (?,?,?,?,?,?)
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, subresponse.getSubresponse_number());
            ps.setTimestamp(2, subresponse.getCreated_at());
            ps.setTimestamp(3, subresponse.getUpdated_at());
            ps.setString(4, subresponse.getComponent_html());
            ps.setString(5, subresponse.getSubresponse_text());
            ps.setInt(6, subresponse.getResponseoptions_id());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubresponse(Subresponse subresponse) {

        String query = """
                UPDATE subresponse_options SET
                subresponse_number = ?,
                created_at = ?,
                updated_at = ?,
                component_html = ?,
                subresponse_text = ?,
                responseoptions_id = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, subresponse.getSubresponse_number());
            ps.setTimestamp(2, subresponse.getCreated_at());
            ps.setTimestamp(3, subresponse.getUpdated_at());
            ps.setString(4, subresponse.getComponent_html());
            ps.setString(5, subresponse.getSubresponse_text());
            ps.setInt(6, subresponse.getResponseoptions_id());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubresponse(int id) {
        String query = "DELETE FROM subresponse_options WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Subresponse> findSubresponseById(int id) {
        String query = """
                SELECT id,
                    subresponse_number,
                    created_at,
                    updated_at,
                    component_html,
                    subresponse_text,
                    responseoptions_id
                FROM subresponse_options
                WHERE id = ?
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    Subresponse subresponse = new Subresponse(
                            rs.getInt("id"),
                            rs.getInt("subresponse_number"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            rs.getString("component_html"),
                            rs.getString("subresponse_text"),
                            rs.getInt("responseoptions_id"));

                    return Optional.of(subresponse);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Subresponse> findAllSubresponse() {
        List<Subresponse> subresponsees = new ArrayList<>();
        String query = "SELECT id, subresponse_number, created_At, updated_At,component_html, subresponse_text, responseoptions_id FROM subresponse_options";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                Subresponse subresponse = new Subresponse(
                        rs.getInt("id"),
                        rs.getInt("subresponse_number"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getString("component_html"),
                        rs.getString("subresponse_text"),
                        rs.getInt("responseoptions_id"));

                subresponsees.add(subresponse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subresponsees;
    }

}
