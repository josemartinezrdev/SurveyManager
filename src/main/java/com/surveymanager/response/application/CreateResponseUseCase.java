package com.surveymanager.response.application;

import com.surveymanager.response.domain.entity.Response;
import com.surveymanager.response.domain.service.ResponseService;

public class CreateResponseUseCase {
    private final ResponseService responseService;

    public CreateResponseUseCase(ResponseService responseService) {
        this.responseService = responseService;
    }

    public void execute(Response response) {
        responseService.createResponse(response);
    }
}
