package com.penguins.project.service;


import com.penguins.project.model.Accomodation.AccomodationRepository;
import org.springframework.stereotype.Service;

@Service
public class AccomodationService {

    private final AccomodationRepository accomodationRepository;

    public AccomodationService(AccomodationRepository accomodationRepository) {
        this.accomodationRepository = accomodationRepository;
    }
}
