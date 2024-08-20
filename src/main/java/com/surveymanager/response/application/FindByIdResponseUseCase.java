package com.surveymanager.response.application;

import com.surveymanager.response.domain.entity.Response;
import com.surveymanager.response.domain.service.ResponseService;

public class FindByIdResponseUseCase {
    private final ResponseService responseService;

    public FindByIdResponseUseCase(ResponseService responseService) {
        this.responseService = responseService;
    }

    public Response execute(int id) {
        return responseService.findByIdResponse(id);
    }
}
