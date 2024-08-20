package com.surveymanager.users.domain;

public class User {
    private int id;
    private Boolean enabled = false;
    private String username;
    private String password;

    public User() {
        this.enabled = false;
    }
    
    public User(int id, Boolean enabled, String username, String password) {
        this.id = id;
        this.enabled = false;
        this.username = username;
        this.password = password;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    


}
