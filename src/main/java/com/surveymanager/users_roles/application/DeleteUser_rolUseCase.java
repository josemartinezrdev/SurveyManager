package com.surveymanager.users_roles.application;

import com.surveymanager.users_roles.domain.User_rolService;

public class DeleteUser_rolUseCase {
    private final User_rolService user_rolService;
    

    public DeleteUser_rolUseCase(User_rolService user_rolService) {
        this.user_rolService = user_rolService;
    }

    public void execute(int user_id, int role_id){
        user_rolService.deleteUser_rol(user_id, role_id);
    }
}
