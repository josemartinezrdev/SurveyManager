package com.surveymanager.users.domain;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    Optional<User> findUserById(int id);
    List<User> findAllUser();
}
