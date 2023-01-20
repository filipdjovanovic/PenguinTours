package com.penguins.project.service;

import com.penguins.project.model.Lokacija.LokacijaRepository;
import org.springframework.stereotype.Service;

@Service
public class LokacijaService {

    private final LokacijaRepository lokacijaRepository;

    public LokacijaService(LokacijaRepository lokacijaRepository) {
        this.lokacijaRepository = lokacijaRepository;
    }
}
