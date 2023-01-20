package com.penguins.project.controller.Smestaj;


import com.penguins.project.service.SmestajService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmestajController {
    private final SmestajService smestajService;

    public SmestajController(SmestajService smestajService) {
        this.smestajService = smestajService;
    }
}
