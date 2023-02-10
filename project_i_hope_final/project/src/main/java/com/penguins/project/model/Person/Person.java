package com.penguins.project.model.Person;

import com.penguins.project.model.Reservation.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String contact;
    private String address;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="person_id")
    private Set<Reservation> reservations = new HashSet<>();

}
