package com.surveymanager.categories_catalog.aplication;

import com.surveymanager.categories_catalog.domain.Categorie_catalogService;
import com.surveymanager.categories_catalog.domain.Categorie_catalog;

public class CreateCategorie_catalogUseCase {
    private final Categorie_catalogService categorie_catalogService;

    public CreateCategorie_catalogUseCase(Categorie_catalogService categorie_catalogService) {
        this.categorie_catalogService = categorie_catalogService;
    }

    public void execute(Categorie_catalog categorie_catalog){
        categorie_catalogService.createCategorie_catalog(categorie_catalog);
    }

}
