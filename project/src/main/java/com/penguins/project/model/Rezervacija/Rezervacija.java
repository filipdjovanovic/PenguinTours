package com.penguins.project.model.Rezervacija;


import com.penguins.project.model.Aranzman.Aranzman;
import com.penguins.project.model.Osoba.Osoba;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rezervacija")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Rezervacija {

    @Id
    @SequenceGenerator(
            name = "rezervacija_sequence",
            sequenceName = "rezervacija_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rezervacija_sequence")
    private Long id;

    private Integer broj_odraslih;
    private Integer broj_dece;
    private String nacin_placanja;

    @Column(columnDefinition = "TEXT")
    private String komentar;

    @ManyToOne
    private Osoba rezervator;

    @ManyToOne
    private Aranzman aranzman;

    /*
    public Rezervacija(Integer broj_odraslih, Integer broj_dece, String nacin_placanja, String komentar, Osoba rezervator, Aranzman aranzman) {
        this.broj_odraslih = broj_odraslih;
        this.broj_dece = broj_dece;
        this.nacin_placanja = nacin_placanja;
        this.komentar = komentar;
        this.rezervator = rezervator;
        this.aranzman = aranzman;
    }

     */
}
