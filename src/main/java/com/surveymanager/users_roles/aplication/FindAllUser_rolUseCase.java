package com.surveymanager.users_roles.aplication;

import java.util.List;

import com.surveymanager.users_roles.domain.User_rol;
import com.surveymanager.users_roles.domain.User_rolService;


public class FindAllUser_rolUseCase {
    private final User_rolService user_rolService;

    public FindAllUser_rolUseCase(User_rolService user_rolService) {
        this.user_rolService = user_rolService;
    }

    public List<User_rol> execute(){
        return user_rolService.findAllUser_rol();
    }

}
