package com.surveymanager.categories_catalog.aplication;

import java.util.Optional;

import com.surveymanager.categories_catalog.domain.Categorie_catalog;
import com.surveymanager.categories_catalog.domain.Categorie_catalogService;


public class FindCategorie_catalogUseCase {
    private final Categorie_catalogService categorie_catalogService;

        public FindCategorie_catalogUseCase(Categorie_catalogService categorie_catalogService) {
        this.categorie_catalogService = categorie_catalogService;
    }
    public Optional<Categorie_catalog> execute(int id){
            return categorie_catalogService.findCategorie_catalogById(id);
        }


}
