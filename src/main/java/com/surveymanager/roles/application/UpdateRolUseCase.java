package com.surveymanager.roles.application;

import com.surveymanager.roles.domain.Rol;
import com.surveymanager.roles.domain.RolService;

public class UpdateRolUseCase {
    private final RolService rolService;

    public UpdateRolUseCase(RolService rolService) {
        this.rolService = rolService;
    }

    public void execute (Rol rol){
        rolService.updateRol(rol);
    }

}
