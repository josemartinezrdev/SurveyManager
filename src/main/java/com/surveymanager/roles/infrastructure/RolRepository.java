package com.surveymanager.roles.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;

import com.surveymanager.roles.domain.Rol;
import com.surveymanager.roles.domain.RolService;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RolRepository implements RolService {

    private Connection connection;
    
    public RolRepository() {
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
    public void createRol(Rol rol) {
        try {
            String query = "INSERT INTO roles(id, name) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, rol.getId());
            ps.setString(2, rol.getName());
            ps.executeUpdate();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Override
    public void updateRol(Rol rol) {
       
        String query = "UPDATE roles SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, rol.getName());
            ps.setInt(2, rol.getId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void deleteRol(int id) {
        String query = "DELETE FROM roles WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Rol> findRolById(int id) {
        String query = "SELECT id, name FROM roles WHERE id = ?";
        try {
             PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Rol rol = new Rol(rs.getInt("id"), rs.getString("name"));
                        return Optional.of(rol);
                    }
                }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Rol> findAllRol() {
        List<Rol> roles = new ArrayList<>();
        String query = "SELECT id, name FROM roles";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("name");
                Rol rol = new Rol(id, nombre);
                roles.add(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    

}
