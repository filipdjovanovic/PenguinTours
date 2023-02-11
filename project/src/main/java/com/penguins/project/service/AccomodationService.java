package com.penguins.project.service;


import com.penguins.project.model.Accomodation.Accomodation;
import com.penguins.project.model.Accomodation.AccomodationRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccomodationService {

    private final AccomodationRepository accomodationRepository;

    public AccomodationService(AccomodationRepository accomodationRepository) {
        this.accomodationRepository = accomodationRepository;
    }

    @Transactional
    public List<Accomodation> findSame(Accomodation accomodation){
        Example<Accomodation> example = Example.of(accomodation);
        return accomodationRepository.findAll(example);
    }
}
