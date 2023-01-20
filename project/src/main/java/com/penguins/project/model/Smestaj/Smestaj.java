package com.penguins.project.model.Smestaj;


import com.penguins.project.model.Aranzman.Aranzman;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "smestaj")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Smestaj {
    @Id
    @SequenceGenerator(
            name = "smestaj_sequence",
            sequenceName = "smestaj_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "smestaj_sequence")
    private Long id;

    private String ime;
    private String kategorija;
    private String tip_smestaja;
    private Boolean tv;
    private Boolean sef;
    private Boolean frizider;
    private Boolean klima;
    private Boolean internet;

    //Mozda da frontend pazi o broju slika
    //private List<Byte[]> slike;

    @Lob
    private Byte[] slika1;
    @Lob
    private Byte[] slika2;
    @Lob
    private Byte[] slika3;
    @Lob
    private Byte[] slika4;
    @Lob
    private Byte[] slika5;
    @Lob
    private Byte[] slika6;

    @ManyToMany(mappedBy = "smestaji")
    private Set<Aranzman> aranzmani = new HashSet<>();

    /*
    public Smestaj(String ime, String kategorija, String tip_smestaja, Boolean TV, Boolean sef, Boolean frizider, Boolean klima, Boolean internet, Byte[] slika1, Byte[] slika2, Byte[] slika3, Byte[] slika4, Byte[] slika5, Byte[] slika6) {
        this.ime = ime;
        this.kategorija = kategorija;
        this.tip_smestaja = tip_smestaja;
        this.TV = TV;
        this.sef = sef;
        this.frizider = frizider;
        this.klima = klima;
        this.internet = internet;
        this.slika1 = slika1;
        this.slika2 = slika2;
        this.slika3 = slika3;
        this.slika4 = slika4;
        this.slika5 = slika5;
        this.slika6 = slika6;
    }

    public Smestaj(String ime, String kategorija, String tip_smestaja, Boolean TV, Boolean sef, Boolean frizider, Boolean klima, Boolean internet, Byte[] slika1, Byte[] slika2, Byte[] slika3, Byte[] slika4, Byte[] slika5, Byte[] slika6, Set<Aranzman> aranzmani) {
        this.ime = ime;
        this.kategorija = kategorija;
        this.tip_smestaja = tip_smestaja;
        this.TV = TV;
        this.sef = sef;
        this.frizider = frizider;
        this.klima = klima;
        this.internet = internet;
        this.slika1 = slika1;
        this.slika2 = slika2;
        this.slika3 = slika3;
        this.slika4 = slika4;
        this.slika5 = slika5;
        this.slika6 = slika6;
        this.aranzmani = aranzmani;
    }

     */
}
