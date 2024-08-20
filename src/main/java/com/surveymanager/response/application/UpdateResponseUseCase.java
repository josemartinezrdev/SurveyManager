package com.surveymanager.response.application;

import com.surveymanager.response.domain.entity.Response;
import com.surveymanager.response.domain.service.ResponseService;

public class UpdateResponseUseCase {

    private final ResponseService responseService;

    public UpdateResponseUseCase(ResponseService responseService) {
        this.responseService = responseService;
    }

    public void execute(Response response) {
        responseService.updateResponse(response);
    }
}
