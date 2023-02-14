package com.penguins.project.controller.Person;


import com.penguins.project.model.Person.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;



@Getter
@Builder
@AllArgsConstructor
public class PersonW {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String contact;
    private String address;

    public PersonW(Person person){
        this.id = person.getId();
        this.name = person.getName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.contact = person.getContact();
        this.address = person.getAddress();

    }
}
