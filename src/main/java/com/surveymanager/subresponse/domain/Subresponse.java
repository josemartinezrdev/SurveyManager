package com.surveymanager.subresponse.domain;

import java.sql.Timestamp;

public class Subresponse {
    private int id;
    private int subresponse_number;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String component_html;
    private String subresponse_text;

    private int responseoptions_id;

    public Subresponse() {
    }

    public Subresponse(int id, int subresponse_number, Timestamp created_at, Timestamp updated_at,
            String component_html, String subresponse_text, int responseoptions_id) {
        this.id = id;
        this.subresponse_number = subresponse_number;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.component_html = component_html;
        this.subresponse_text = subresponse_text;
        this.responseoptions_id = responseoptions_id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubresponse_number() {
        return this.subresponse_number;
    }

    public void setSubresponse_number(int subresponse_number) {
        this.subresponse_number = subresponse_number;
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

    public String getComponent_html() {
        return this.component_html;
    }

    public void setComponent_html(String component_html) {
        this.component_html = component_html;
    }

    public String getSubresponse_text() {
        return this.subresponse_text;
    }

    public void setSubresponse_text(String subresponse_text) {
        this.subresponse_text = subresponse_text;
    }

    public int getResponseoptions_id() {
        return this.responseoptions_id;
    }

    public void setResponseoptions_id(int responseoptions_id) {
        this.responseoptions_id = responseoptions_id;
    }

    public void updateDate() {
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public void createdDate() {
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

}
