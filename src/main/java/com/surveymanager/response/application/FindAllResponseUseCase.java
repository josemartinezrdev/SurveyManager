package com.surveymanager.response.application;

import java.util.List;

import com.surveymanager.response.domain.entity.Response;
import com.surveymanager.response.domain.service.ResponseService;

public class FindAllResponseUseCase {
    private final ResponseService responseService;

    public FindAllResponseUseCase(ResponseService responseService) {
        this.responseService = responseService;
    }

    public List<Response> execute() {
        return responseService.findAllResponses();
    }
}
