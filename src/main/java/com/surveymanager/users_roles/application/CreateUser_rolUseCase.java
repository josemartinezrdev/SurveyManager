package com.surveymanager.users_roles.application;

import com.surveymanager.users_roles.domain.User_rol;
import com.surveymanager.users_roles.domain.User_rolService;

public class CreateUser_rolUseCase {
    private final User_rolService user_rolService;

    public CreateUser_rolUseCase(User_rolService user_rolService) {
        this.user_rolService = user_rolService;
    }

    public void execute(User_rol user_rol){
        user_rolService.createUser_rol(user_rol);
    }

}
