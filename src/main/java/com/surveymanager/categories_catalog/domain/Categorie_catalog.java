package com.surveymanager.categories_catalog.domain;

import java.sql.Timestamp;

public class Categorie_catalog {
    private int id;
    private Timestamp created_At = null;
    private Timestamp updated_At;
    private String name;

    public Categorie_catalog() {
    }

    public Categorie_catalog(int id, Timestamp created_At, Timestamp updated_At, String name) {
        this.id = id;
        this.created_At = created_At;
        this.updated_At = updated_At;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated_At() {
        return this.created_At;
    }

    public void setCreated_At(Timestamp created_At) {
        this.created_At = created_At;
    }

    public Timestamp getUpdated_At() {
        return this.updated_At;
    }

    public void setUpdated_At(Timestamp updated_At) {
        this.updated_At = updated_At;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void updateDate() {
        this.updated_At = new Timestamp(System.currentTimeMillis());
    }

    public void createdDate() {
        this.created_At = new Timestamp(System.currentTimeMillis());
    }

}
