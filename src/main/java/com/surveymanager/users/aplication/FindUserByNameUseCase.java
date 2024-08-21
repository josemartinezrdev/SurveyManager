package com.surveymanager.users.aplication;

import com.surveymanager.users.domain.UserService;

public class FindUserByNameUseCase {
    private final UserService userService;

    public FindUserByNameUseCase(UserService userService) {
        this.userService = userService;
    }

    public Boolean execute(String name, String password){
       return userService.findUserByName(name, password);
    }

}
