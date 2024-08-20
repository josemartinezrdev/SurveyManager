package com.surveymanager.response.domain.entity;

import java.sql.Timestamp;

public class Response {

    private int id;
    private int optionValue;
    private Timestamp created_at = null;
    private Timestamp updated_at;
    private String typeComponentHtml;
    private String commentResponse;
    private String optionText;

    private int categoryCatalogId;
    private int questionId;
    private int parentResponseId;

    public Response() {
    }

    public Response(int id, int optionValue, Timestamp created_at, Timestamp updated_at, String typeComponentHtml,
            String commentResponse, String optionText, int categoryCatalogId, int questionId, int parentResponseId) {
        this.id = id;
        this.optionValue = optionValue;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.typeComponentHtml = typeComponentHtml;
        this.commentResponse = commentResponse;
        this.optionText = optionText;
        this.categoryCatalogId = categoryCatalogId;
        this.questionId = questionId;
        this.parentResponseId = parentResponseId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOptionValue() {
        return this.optionValue;
    }

    public void setOptionValue(int optionValue) {
        this.optionValue = optionValue;
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

    public String getTypeComponentHtml() {
        return this.typeComponentHtml;
    }

    public void setTypeComponentHtml(String typeComponentHtml) {
        this.typeComponentHtml = typeComponentHtml;
    }

    public String getCommentResponse() {
        return this.commentResponse;
    }

    public void setCommentResponse(String commentResponse) {
        this.commentResponse = commentResponse;
    }

    public String getOptionText() {
        return this.optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public int getCategoryCatalogId() {
        return this.categoryCatalogId;
    }

    public void setCategoryCatalogId(int categoryCatalogId) {
        this.categoryCatalogId = categoryCatalogId;
    }

    public int getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getParentResponseId() {
        return this.parentResponseId;
    }

    public void setParentResponseId(int parentResponseId) {
        this.parentResponseId = parentResponseId;
    }

    public void updateDate() {
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public void createdDate() {
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

}
