package com.penguins.project.controller.Lokacija;


import com.penguins.project.service.LokacijaService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LokacijaController {

    private final LokacijaService lokacijaService;

    public LokacijaController(LokacijaService lokacijaService) {
        this.lokacijaService = lokacijaService;
    }
}
