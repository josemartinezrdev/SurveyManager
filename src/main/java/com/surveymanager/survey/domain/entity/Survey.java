package com.surveymanager.survey.domain.entity;

import java.sql.Timestamp;

public class Survey {

    private int id;
    private Timestamp created_at = null;
    private Timestamp updated_at;
    private String description;
    private String name;

    public Survey() {
        this.created_at = (this.created_at == null) ? new Timestamp(System.currentTimeMillis()) : this.created_at;
    }

    public Survey(int id, Timestamp created_at, Timestamp updated_at, String description, String name) {
        this.id = id;
        this.created_at = (this.created_at == null) ? new Timestamp(System.currentTimeMillis()) : this.created_at;
        this.updated_at = updated_at;
        this.description = description;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateDate() {
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }
}
