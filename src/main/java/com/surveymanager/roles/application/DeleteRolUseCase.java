package com.surveymanager.roles.application;

import com.surveymanager.roles.domain.RolService;

public class DeleteRolUseCase {
    private final RolService rolService;
    

    public DeleteRolUseCase(RolService rolService) {
        this.rolService = rolService;
    }

    public void execute(int id){
        rolService.deleteRol(id);
    }
}
