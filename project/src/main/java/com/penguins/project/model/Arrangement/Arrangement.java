package com.penguins.project.model.Arrangement;


import com.penguins.project.model.Location.Location;
import com.penguins.project.model.Program.Program;
import com.penguins.project.model.Reservation.Reservation;
import com.penguins.project.model.Accomodation.Accomodation;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column(unique=true)
    private String name;
    private Integer price;
    private String transportation;
    private String status;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrangement_id")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrangement_id")
    private Set<Program> programs = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "accomodation_arrangement", joinColumns = @JoinColumn(name = "arrangement_id"), inverseJoinColumns = @JoinColumn(name = "accomodation_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Accomodation> accomodations = new HashSet<>();

}
