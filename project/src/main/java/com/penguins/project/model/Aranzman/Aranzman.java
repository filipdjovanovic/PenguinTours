package com.penguins.project.model.Aranzman;


import com.penguins.project.model.Program.Program;
import com.penguins.project.model.Rezervacija.Rezervacija;
import com.penguins.project.model.Smestaj.Smestaj;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "aranzman")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Aranzman {

    @Id
    @SequenceGenerator(
            name = "aranzman_sequence",
            sequenceName = "aranzman_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "aranzman_sequence")
    private Long id;
    private String naziv;
    private Integer cena;
    private String prevoz;
    private String status;

    //private Integer broj_dana;

    @Column(columnDefinition = "TEXT")
    private String napomena;


    @OneToMany
    @JoinColumn(name="aranzman_id")
    private Set<Rezervacija> rezervacije = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="aranzman_id")
    private Set<Program> programi= new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "smestaj_aranzman", joinColumns = @JoinColumn(name = "aranzman_id"),inverseJoinColumns = @JoinColumn(name="smestaj_id"))
    private Set<Smestaj> smestaji = new HashSet<>();

    /*
    public Aranzman(String naziv, Integer cena, String prevoz, String status, String napomena) {
        this.naziv = naziv;
        this.cena = cena;
        this.prevoz = prevoz;
        this.status = status;
        this.napomena = napomena;
    }

     */
}
