package com.surveymanager.users.aplication;

import java.util.List;

import com.surveymanager.users.domain.User;
import com.surveymanager.users.domain.UserService;

public class FindAllUserUseCase {
    private final UserService userService;

    public FindAllUserUseCase(UserService userService) {
        this.userService = userService;
    }

     public List<User> execute(){
        return userService.findAllUser();
    }


}
