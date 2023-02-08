package com.penguins.project.model.Program;

import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Location.Location;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "program")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Program {

    @Id
    @SequenceGenerator(
            name = "program_sequence",
            sequenceName = "program_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "program_sequence")
    @Column(name = "id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate date;

    @ManyToOne
    private Arrangement arrangement;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "program_location",joinColumns = @JoinColumn(name= "program_id"), inverseJoinColumns = @JoinColumn(name="location_id"))
    private Set<Location> locations = new HashSet<>();

}
