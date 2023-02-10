package com.penguins.project.controller.Person;


import com.penguins.project.model.Person.Person;
import lombok.Getter;

@Getter
public class PersonParam {

    private String name;
    private String lastName;
    private String email;
    private String contact;
    private String address;


    public Person toPerson(){

        Person person = Person.builder()
                .name(this.getName())
                .lastName(this.getLastName())
                .email(this.getEmail())
                .contact(this.getContact())
                .address(this.getAddress())
                .build();

        return person;

    }
}
