package com.surveymanager.roles.aplication;

import com.surveymanager.roles.domain.Rol;
import com.surveymanager.roles.domain.RolService;

public class CreateRolUseCase {
    private final RolService rolService;

    public CreateRolUseCase(RolService rolService) {
        this.rolService = rolService;
    }

    public void execute(Rol rol){
        rolService.createRol(rol);
    }

}
