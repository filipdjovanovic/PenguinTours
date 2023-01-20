package com.penguins.project.model.Program;

import com.penguins.project.model.Aranzman.Aranzman;
import com.penguins.project.model.Lokacija.Lokacija;
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
    private String opis;

    private LocalDate datum;

    @ManyToOne
    private Aranzman aranzman;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "program_lokacija",joinColumns = @JoinColumn(name= "program_id"), inverseJoinColumns = @JoinColumn(name="lokacija_id"))
    private Set<Lokacija> lokacije = new HashSet<>();

    /*
    public Program(String opis, LocalDate datum, Aranzman aranzman, Set<Lokacija> lokacije) {
        this.opis = opis;
        this.datum = datum;
        this.aranzman = aranzman;
        this.lokacije = lokacije;
    }

    public Program(Long id, String opis, LocalDate datum, Aranzman aranzman) {
        this.id = id;
        this.opis = opis;
        this.datum = datum;
        this.aranzman = aranzman;
    }

     */
}
