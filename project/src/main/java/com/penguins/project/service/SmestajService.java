package com.penguins.project.service;


import com.penguins.project.model.Smestaj.SmestajRepository;
import org.springframework.stereotype.Service;

@Service
public class SmestajService {

    private final SmestajRepository smestajRepository;

    public SmestajService(SmestajRepository smestajRepository) {
        this.smestajRepository = smestajRepository;
    }
}
