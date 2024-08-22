package com.surveymanager.users.application;

import com.surveymanager.users.domain.User;
import com.surveymanager.users.domain.UserService;

public class CreateUserUseCase {
    private final UserService userService;

    public CreateUserUseCase(UserService userService) {
        this.userService = userService;
    }
    public void execute(User user){
        userService.createUser(user);
    }

    

}