package com.penguins.project.service;

import com.penguins.project.model.Osoba.OsobaRepository;
import org.springframework.stereotype.Service;

@Service
public class OsobaService {

    private final OsobaRepository osobaRepository;

    public OsobaService(OsobaRepository osobaRepository) {
        this.osobaRepository = osobaRepository;
    }
}
