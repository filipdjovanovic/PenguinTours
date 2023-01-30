package com.penguins.project.controller.Accomodation;


import com.penguins.project.service.AccomodationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccomodationController {
    private final AccomodationService accomodationService;

    public AccomodationController(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
    }
}
