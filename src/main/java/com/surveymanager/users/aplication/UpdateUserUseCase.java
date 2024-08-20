package com.surveymanager.users.aplication;

import com.surveymanager.users.domain.User;
import com.surveymanager.users.domain.UserService;

public class UpdateUserUseCase {
    private final UserService userService;

    public UpdateUserUseCase(UserService userService) {
        this.userService = userService;
    }
    public void execute (User user){
        userService.updateUser(user);
    }
}
