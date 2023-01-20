package com.penguins.project.controller.Osoba;


import com.penguins.project.service.OsobaService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OsobaController {

    private final OsobaService osobaService;

    public OsobaController(OsobaService osobaService) {
        this.osobaService = osobaService;
    }
}
