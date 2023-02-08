package com.penguins.project.service;

import com.penguins.project.model.Person.Person;
import com.penguins.project.model.Person.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public List<Person> findByNameAndLastNameAndEmailAndContactAndAddress(String name, String lastName, String email, String contact, String address){
        return personRepository.findByNameAndLastNameAndEmailAndContactAndAddress(name,lastName,email,contact,address);
    }

}
