package com.surveymanager.categories_catalog.application;

import com.surveymanager.categories_catalog.domain.Categorie_catalog;
import com.surveymanager.categories_catalog.domain.Categorie_catalogService;

public class UpdateCategorie_catalogUseCase {
    private final Categorie_catalogService categorie_catalogService;

    public UpdateCategorie_catalogUseCase(Categorie_catalogService categorie_catalogService) {
        this.categorie_catalogService = categorie_catalogService;
    }

    public void execute (Categorie_catalog categorie_catalog){
        categorie_catalogService.updateCategorie_catalog(categorie_catalog);
    }

}
