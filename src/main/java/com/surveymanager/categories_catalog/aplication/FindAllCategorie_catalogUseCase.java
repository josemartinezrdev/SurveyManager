package com.surveymanager.categories_catalog.aplication;

import java.util.List;

import com.surveymanager.categories_catalog.domain.Categorie_catalog;
import com.surveymanager.categories_catalog.domain.Categorie_catalogService;


public class FindAllCategorie_catalogUseCase {
    private final Categorie_catalogService categorie_catalogService;

    public FindAllCategorie_catalogUseCase(Categorie_catalogService categorie_catalogService) {
        this.categorie_catalogService = categorie_catalogService;
    }

    public List<Categorie_catalog> execute(){
        return categorie_catalogService.findAllCategorie_catalog();
    }

}
