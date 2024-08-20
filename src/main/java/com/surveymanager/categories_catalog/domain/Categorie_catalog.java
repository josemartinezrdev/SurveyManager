package com.surveymanager.categories_catalog.domain;

import java.time.LocalDateTime;

public class Categorie_catalog {
    private int id;
    private LocalDateTime created_At;
    private LocalDateTime updated_At;
    private String name;

    public Categorie_catalog() {
    }

    public Categorie_catalog(int id, LocalDateTime created_At, LocalDateTime updated_At, String name) {
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

    public LocalDateTime getCreated_At() {
        return this.created_At;
    }

    public void setCreated_At(LocalDateTime created_At) {
        this.created_At = created_At;
    }

    public LocalDateTime getUpdated_At() {
        return this.updated_At;
    }

    public void setUpdated_At(LocalDateTime updated_At) {
        this.updated_At = updated_At;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

}
