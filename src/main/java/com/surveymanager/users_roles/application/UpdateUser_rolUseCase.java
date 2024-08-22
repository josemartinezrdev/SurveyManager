package com.surveymanager.users_roles.application;

import com.surveymanager.users_roles.domain.User_rol;
import com.surveymanager.users_roles.domain.User_rolService;

public class UpdateUser_rolUseCase {
    private final User_rolService user_rolService;

    public UpdateUser_rolUseCase(User_rolService user_rolService) {
        this.user_rolService = user_rolService;
    }

    public void execute (User_rol user_rol){
        user_rolService.updateUser_rol(user_rol);
    }

}
