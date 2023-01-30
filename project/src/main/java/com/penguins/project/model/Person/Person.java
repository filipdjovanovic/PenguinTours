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
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence")
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String contant;
    private String adress;
    @OneToMany
    @JoinColumn(name="person_id")
    private Set<Reservation> reservations = new HashSet<>();

}
