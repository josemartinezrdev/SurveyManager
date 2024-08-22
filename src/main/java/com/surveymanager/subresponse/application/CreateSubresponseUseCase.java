package com.surveymanager.subresponse.application;

import com.surveymanager.subresponse.domain.Subresponse;
import com.surveymanager.subresponse.domain.SubresponseService;

public class CreateSubresponseUseCase {

    private final SubresponseService subresponseService;

    public CreateSubresponseUseCase(SubresponseService subresponseService) {
        this.subresponseService = subresponseService;
    }


    public void execute(Subresponse subresponse){
        subresponseService.createSubresponse(subresponse);
    }

}
