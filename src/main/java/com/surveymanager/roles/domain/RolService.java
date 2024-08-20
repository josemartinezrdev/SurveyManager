package com.surveymanager.roles.domain;
import java.util.List;
import java.util.Optional;

public interface RolService {
    void createRol(Rol rol);
    void updateRol(Rol rol);
    void deleteRol(int id);
    Optional<Rol> findRolById(int id);
    List<Rol> findAllRol();
}
