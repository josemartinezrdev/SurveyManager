package com.surveymanager.categories_catalog.application;

import com.surveymanager.categories_catalog.domain.Categorie_catalogService;

public class DeleteCategorie_catalogUseCase {
    private final Categorie_catalogService categorie_catalogService;
    

    public DeleteCategorie_catalogUseCase(Categorie_catalogService categorie_catalogService) {
        this.categorie_catalogService = categorie_catalogService;
    }

    public void execute(int id){
        categorie_catalogService.deleteCategorie_catalog(id);
    }
}
