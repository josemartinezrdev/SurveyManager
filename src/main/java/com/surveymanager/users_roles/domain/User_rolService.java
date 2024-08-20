package com.surveymanager.users_roles.domain;
import java.util.List;
import java.util.Optional;

public interface User_rolService {
    void createUser_rol(User_rol user_rol);
    void updateUser_rol(User_rol user_rol);
    void deleteUser_rol(int user_id,  int role_id);
    Optional<User_rol> findUser_rolById(int user_id,  int role_id);
    List<User_rol> findAllUser_rol();
}
