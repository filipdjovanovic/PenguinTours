package com.penguins.project.model.Lokacija;

import com.penguins.project.model.Program.Program;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "lokacija")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Lokacija {

    @Id
    @SequenceGenerator(
            name = "lokacija_sequence",
            sequenceName = "lokacija_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lokacija_sequence")
    private Long id;
    private String grad;
    private String drzava;
    private String kontinent;
    @Lob
    private Byte[] slika;

    @ManyToMany(mappedBy = "lokacije")
    private Set<Program> programi = new HashSet<>();


    /*
    public Lokacija(String grad, String drzava, String kontinent, Byte[] slika, Set<Program> programi) {
        this.grad = grad;
        this.drzava = drzava;
        this.kontinent = kontinent;
        this.slika = slika;
        this.programi = programi;
    }

    public Lokacija(Long id, String grad, String drzava, String kontinent, Byte[] slika) {
        this.id = id;
        this.grad = grad;
        this.drzava = drzava;
        this.kontinent = kontinent;
        this.slika = slika;
    }

     */
}
