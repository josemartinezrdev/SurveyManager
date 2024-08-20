package com.surveymanager.users_roles.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;

import com.surveymanager.users_roles.domain.User_rol;
import com.surveymanager.users_roles.domain.User_rolService;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User_rolRepository implements User_rolService {

    private Connection connection;
    
    public User_rolRepository() {
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
    public void createUser_rol(User_rol user_rol) {
        try {
            String query = "INSERT INTO users_roles(user_id, role_id) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, user_rol.getUser_id());
            ps.setInt(2, user_rol.getRole_id());
            ps.executeUpdate();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Override
    public void updateUser_rol(User_rol user_rol) {
       
        String query = "UPDATE users_roles SET user_id = ?, role_id= ?  WHERE user_id = ? AND role_id= ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, user_rol.getUser_id());
            ps.setInt(2, user_rol.getRole_id());
            ps.setInt(3, user_rol.getUser_id());
            ps.setInt(4, user_rol.getRole_id());
            ps.executeUpdate();
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void deleteUser_rol(int user_id, int rol_id) {
        String query = "DELETE FROM users_roles WHERE user_id = ? AND role_id= ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, user_id);
            ps.setInt(1, rol_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User_rol> findUser_rolById(int user_id, int role_id) {
        String query = "SELECT user_id, role_id FROM users_roles WHERE user_id = ? AND role_id= ?";
        try {
             PreparedStatement ps = connection.prepareStatement(query);
             ps.setInt(1, user_id);
             ps.setInt(2, role_id);
            try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        User_rol user_rol = new User_rol(rs.getInt("user_id"), rs.getInt("role_id"));
                        return Optional.of(user_rol);
                    }
                }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User_rol> findAllUser_rol() {
        List<User_rol> users_user_roles = new ArrayList<>();
        String query = "SELECT user_id, role_id FROM users_roles";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                int rol_id = rs.getInt("role_id");
                User_rol user_rol = new User_rol(user_id, rol_id);
                users_user_roles.add(user_rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users_user_roles;
    }

    

}
