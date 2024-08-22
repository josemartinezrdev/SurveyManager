
package com.surveymanager.subresponse.application;

import com.surveymanager.subresponse.domain.SubresponseService;

public class DeleteSubresponseUseCase {
    private final SubresponseService subresponseService;
    

    public DeleteSubresponseUseCase(SubresponseService subresponseService) {
        this.subresponseService = subresponseService;
    }

    public void execute(int id){
        subresponseService.deleteSubresponse(id);
    }
}
