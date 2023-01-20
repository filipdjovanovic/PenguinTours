package com.penguins.project.model.Osoba;

import com.penguins.project.model.Rezervacija.Rezervacija;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "osoba")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Osoba {

    @Id
    @SequenceGenerator(
            name = "osoba_sequence",
            sequenceName = "osoba_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "osoba_sequence")
    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String kontakt;
    private String adresa;
    @OneToMany
    @JoinColumn(name="osoba_id")
    private Set<Rezervacija> rezervacije = new HashSet<>();


    /*
    public Osoba(String ime, String prezime, String email, String kontakt, String adresa, Set<Rezervacija> rezervacije) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.kontakt = kontakt;
        this.adresa = adresa;
        this.rezervacije = rezervacije;
    }

    public Osoba(String ime, String prezime, String email, String kontakt, String adresa) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.kontakt = kontakt;
        this.adresa = adresa;
    }

     */
}
