package com.surveymanager.subresponse.domain;


import java.util.List;
import java.util.Optional;

public interface SubresponseService {
    void createSubresponse(Subresponse subresponse);
    void updateSubresponse(Subresponse subresponse);
    void deleteSubresponse(int id);
    Optional<Subresponse> findSubresponseById(int id);
    List<Subresponse> findAllSubresponse();
}
