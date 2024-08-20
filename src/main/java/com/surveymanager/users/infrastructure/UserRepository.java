package com.surveymanager.users.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.surveymanager.users.domain.User;
import com.surveymanager.users.domain.UserService;

public class UserRepository implements UserService{

    private Connection connection;
    
    public UserRepository() {
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
    public void createUser(User user) {
       String query = """
               INSERT INTO users(username, password) VALUES( ?,?)
               """;
        try {
             PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
            }

    @Override
    public void updateUser(User user) {
        
        String query = "UPDATE users SET username = ?, password = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findUserById(int id) {
          String query = "SELECT id, username, password FROM users WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User(rs.getInt("id"),rs.getBoolean("enabled"), rs.getString("username"), rs.getString("password"));
                    return Optional.of(user);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllUser() {
         List<User> useres = new ArrayList<>();
        String query = "SELECT id, username, password FROM users";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Boolean enable = rs.getBoolean("enabled");
                String username = rs.getString("username");
                String password = rs.getString("password");

                User user = new User(id, enable, username, password);
                useres.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return useres;
    }
    

}
