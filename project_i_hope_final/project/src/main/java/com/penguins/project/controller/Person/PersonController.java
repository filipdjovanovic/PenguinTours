package com.penguins.project.controller.Person;


import com.penguins.project.service.PersonService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
}
