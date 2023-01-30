package com.penguins.project.model.Arrangement;


import com.penguins.project.model.Program.Program;
import com.penguins.project.model.Reservation.Reservation;
import com.penguins.project.model.Accomodation.Accomodation;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "arrangement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Arrangement {

    @Id
    @SequenceGenerator(
            name = "arrangement_sequence",
            sequenceName = "arrangement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "arrangement_sequence")
    private Long id;
    private String name;
    private Integer price;
    private String transportation;
    private String status;

    //private Integer broj_dana;

    @Column(columnDefinition = "TEXT")
    private String remark;


    @OneToMany
    @JoinColumn(name = "arrangement_id")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrangement_id")
    private Set<Program> programs = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "accomodation_arrangement", joinColumns = @JoinColumn(name = "arrangement_id"), inverseJoinColumns = @JoinColumn(name = "accomodation_id"))
    private Set<Accomodation> accomodations = new HashSet<>();

}
