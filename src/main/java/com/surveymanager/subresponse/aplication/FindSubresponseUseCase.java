package com.surveymanager.subresponse.aplication;

import java.util.Optional;

import com.surveymanager.subresponse.domain.Subresponse;
import com.surveymanager.subresponse.domain.SubresponseService;



public class FindSubresponseUseCase {
    private final SubresponseService subresponseService;

        public FindSubresponseUseCase(SubresponseService subresponseService) {
        this.subresponseService = subresponseService;
    }
    public Optional<Subresponse> execute(int id){
            return subresponseService.findSubresponseById(id);
        }


}
