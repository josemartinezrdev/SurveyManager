package com.surveymanager.roles.application;

import java.util.List;

import com.surveymanager.roles.domain.Rol;
import com.surveymanager.roles.domain.RolService;


public class FindAllRolUseCase {
    private final RolService rolService;

    public FindAllRolUseCase(RolService rolService) {
        this.rolService = rolService;
    }

    public List<Rol> execute(){
        return rolService.findAllRol();
    }

}
