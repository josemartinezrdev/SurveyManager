package com.surveymanager.response.application;

import com.surveymanager.response.domain.service.ResponseService;

public class DeleteResponseUseCase {
    private final ResponseService responseService;

    public DeleteResponseUseCase(ResponseService responseService) {
        this.responseService = responseService;
    }

    public void execute(int id) {
        responseService.deleteResponse(id);
    }
}
