package com.surveymanager.response.domain.service;

import java.util.List;

import com.surveymanager.response.domain.entity.Response;

public interface ResponseService {
    public void createResponse(Response response);

    public void updateResponse(Response response);

    public void deleteResponse(int id);

    public List<Response> findAllResponses();

    public Response findByIdResponse(int id);
}
