package com.surveymanager.users_roles.domain;

public class User_rol {
    private int user_id;
    private int role_id;

    public User_rol() {
    }

    public User_rol(int user_id, int role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRole_id() {
        return this.role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }


}
