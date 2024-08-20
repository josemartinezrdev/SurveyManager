package com.surveymanager.users_roles.aplication;

import java.util.Optional;

import com.surveymanager.users_roles.domain.User_rol;
import com.surveymanager.users_roles.domain.User_rolService;


public class FindUser_rolUseCase {
    private final User_rolService user_rolService;

        public FindUser_rolUseCase(User_rolService user_rolService) {
        this.user_rolService = user_rolService;
    }
    public Optional<User_rol> execute(int user_id, int role_id){
            return user_rolService.findUser_rolById(user_id, role_id);
        }


}
