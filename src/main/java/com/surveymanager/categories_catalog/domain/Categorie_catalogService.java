package com.surveymanager.categories_catalog.domain;
import java.util.List;
import java.util.Optional;

public interface Categorie_catalogService {
    void createCategorie_catalog(Categorie_catalog categorie_catalog);
    void updateCategorie_catalog(Categorie_catalog categorie_catalog);
    void deleteCategorie_catalog(int id);
    Optional<Categorie_catalog> findCategorie_catalogById(int id);
    List<Categorie_catalog> findAllCategorie_catalog();
}
