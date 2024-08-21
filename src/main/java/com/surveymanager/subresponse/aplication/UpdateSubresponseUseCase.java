package com.surveymanager.subresponse.aplication;

import com.surveymanager.subresponse.domain.Subresponse;
import com.surveymanager.subresponse.domain.SubresponseService;

public class UpdateSubresponseUseCase {
    private final SubresponseService subresponseService;

    public UpdateSubresponseUseCase(SubresponseService subresponseService) {
        this.subresponseService = subresponseService;
    }

    public void execute (Subresponse subresponse){
        subresponseService.updateSubresponse(subresponse);
    }

}
