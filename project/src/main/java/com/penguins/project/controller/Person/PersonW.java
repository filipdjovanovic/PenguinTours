package com.penguins.project.controller.Person;


import com.penguins.project.controller.Reservation.ReservationW;
import com.penguins.project.model.Person.Person;
import com.penguins.project.model.Reservation.Reservation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
