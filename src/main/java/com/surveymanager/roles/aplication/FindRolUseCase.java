package com.surveymanager.roles.aplication;

import java.util.Optional;

import com.surveymanager.roles.domain.Rol;
import com.surveymanager.roles.domain.RolService;


public class FindRolUseCase {
    private final RolService rolService;

        public FindRolUseCase(RolService rolService) {
        this.rolService = rolService;
    }
    public Optional<Rol> execute(int id){
            return rolService.findRolById(id);
        }


}
