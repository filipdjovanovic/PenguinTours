package com.penguins.project.service;

import com.penguins.project.model.Rezervacija.RezervacijaRepository;
import org.springframework.stereotype.Service;

@Service
public class RezervacijaService {

    private final RezervacijaRepository rezervacijaRepository;


    public RezervacijaService(RezervacijaRepository rezervacijaRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
    }
}
