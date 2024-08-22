package com.surveymanager.subresponse.application;
import java.util.List;

import com.surveymanager.subresponse.domain.Subresponse;
import com.surveymanager.subresponse.domain.SubresponseService;

public class FindAllSubresponseUseCase {
    private final SubresponseService subresponseService;

    public FindAllSubresponseUseCase(SubresponseService subresponseService) {
        this.subresponseService = subresponseService;
    }

    public List<Subresponse> execute(){
        return subresponseService.findAllSubresponse();
    }

}
