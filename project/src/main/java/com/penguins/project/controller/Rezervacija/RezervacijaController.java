package com.penguins.project.controller.Rezervacija;


import com.penguins.project.service.RezervacijaService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RezervacijaController {
    private final RezervacijaService rezervacijaService;

    public RezervacijaController(RezervacijaService rezervacijaService) {
        this.rezervacijaService = rezervacijaService;
    }
}
