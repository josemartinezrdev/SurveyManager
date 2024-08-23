package com.surveymanager.principalui.surveyUser.domain;

public class SurveyUser {
    private int id;
    private int response_id;
    private Integer subresponse_id;
    private String responsetext;

    public SurveyUser() {
    }

    public SurveyUser(int id, int response_id, Integer subresponse_id, String responsetext) {
        this.id = id;
        this.response_id = response_id;
        this.subresponse_id = subresponse_id;
        this.responsetext = responsetext;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResponse_id() {
        return this.response_id;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public Integer getSubresponse_id() {
        return this.subresponse_id;
    }

    public void setSubresponse_id(Integer subresponse_id) {
        this.subresponse_id = subresponse_id;
    }

    public String getResponsetext() {
        return this.responsetext;
    }

    public void setResponsetext(String responsetext) {
        this.responsetext = responsetext;
    }

}
