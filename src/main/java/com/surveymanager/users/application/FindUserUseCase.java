package com.surveymanager.users.application;

import java.util.Optional;

import com.surveymanager.users.domain.User;
import com.surveymanager.users.domain.UserService;

public class FindUserUseCase {
    private final UserService userService;

    public FindUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public Optional<User> execute(int id) {
        return userService.findUserById(id);
    }

}
