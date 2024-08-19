package com.surveymanager.chapter.domain.entity;

import java.sql.Timestamp;

public class Chapter {

    private int id;
    private Timestamp created_at = null;
    private Timestamp updated_at;
    private String chapter_number;
    private String chapter_title;

    private int survey_id;

    public Chapter() {
        this.created_at = (this.created_at == null) ? new Timestamp(System.currentTimeMillis()) : this.created_at;
    }

    public Chapter(int id, Timestamp created_at, Timestamp updated_at, String chapter_number, String chapter_title,
            int survey_id) {
        this.id = id;
        this.created_at = (this.created_at == null) ? new Timestamp(System.currentTimeMillis()) : this.created_at;
        this.updated_at = updated_at;
        this.chapter_number = chapter_number;
        this.chapter_title = chapter_title;
        this.survey_id = survey_id;
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

    public String getChapter_number() {
        return this.chapter_number;
    }

    public void setChapter_number(String chapter_number) {
        this.chapter_number = chapter_number;
    }

    public String getChapter_title() {
        return this.chapter_title;
    }

    public void setChapter_title(String chapter_title) {
        this.chapter_title = chapter_title;
    }

    public int getSurvey_id() {
        return this.survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public void updateDate() {
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

}
