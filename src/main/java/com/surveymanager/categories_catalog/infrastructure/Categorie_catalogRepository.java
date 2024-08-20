package com.surveymanager.categories_catalog.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;

import com.surveymanager.categories_catalog.domain.Categorie_catalog;
import com.surveymanager.categories_catalog.domain.Categorie_catalogService;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Categorie_catalogRepository implements Categorie_catalogService {

    private Connection connection;

    public Categorie_catalogRepository() {
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
    public void createCategorie_catalog(Categorie_catalog categorie_catalog) {
        try {
            String query = "INSERT INTO categories_catalog(id, created_At,updated_At, name) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, categorie_catalog.getId());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(categorie_catalog.getCreated_At()));
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(categorie_catalog.getUpdated_At()));
            ps.setString(4, categorie_catalog.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategorie_catalog(Categorie_catalog categorie_catalog) {

        String query = "UPDATE categories_catalog SET name = ?, updated_At = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, categorie_catalog.getName());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(categorie_catalog.getUpdated_At()));
            ps.setInt(3, categorie_catalog.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategorie_catalog(int id) {
        String query = "DELETE FROM categories_catalog WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Categorie_catalog> findCategorie_catalogById(int id) {
        String query = "SELECT id, created_At, updated_At, name FROM categories_catalog WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    LocalDateTime createdAt = rs.getTimestamp("created_At").toLocalDateTime();
                    LocalDateTime updatedAt = rs.getTimestamp("updated_At").toLocalDateTime();

                    Categorie_catalog categorie_catalog = new Categorie_catalog(
                            rs.getInt("id"),
                            createdAt,
                            updatedAt,
                            rs.getString("name"));

                    return Optional.of(categorie_catalog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Categorie_catalog> findAllCategorie_catalog() {
        List<Categorie_catalog> categorie_cataloges = new ArrayList<>();
        String query = "SELECT id, created_At, updated_At, name FROM categories_catalog";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");

                LocalDateTime createdAt = rs.getTimestamp("created_At").toLocalDateTime();
                LocalDateTime updatedAt = rs.getTimestamp("updated_At").toLocalDateTime();

                Categorie_catalog categorie_catalog = new Categorie_catalog(id, createdAt, updatedAt, name);
                categorie_cataloges.add(categorie_catalog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorie_cataloges;
    }

}
