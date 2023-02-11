package com.penguins.project.model.Location;

import com.penguins.project.model.Accomodation.Accomodation;
import com.penguins.project.model.Program.Program;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "location")
        //uniqueConstraints={@UniqueConstraint(name="city_country_continent", columnNames={"city", "country","continent"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Location {

    @Id
    @SequenceGenerator(
            name = "location_sequence",
            sequenceName = "location_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "location_sequence")
    private Long id;
    private String city;
    private String country;
    private String continent;
    @Lob
    private Byte[] picture;

    @ManyToMany(mappedBy = "locations",cascade = CascadeType.ALL)
    private Set<Program> programs = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Set<Accomodation> accomodations = new HashSet<>();

    /*
    public Locationa(String grad, String drzava, String kontinent, Byte[] slika, Set<Program> programi) {
        this.grad = grad;
        this.drzava = drzava;
        this.kontinent = kontinent;
        this.slika = slika;
        this.programi = programi;
    }

    public Locationa(Long id, String grad, String drzava, String kontinent, Byte[] slika) {
        this.id = id;
        this.grad = grad;
        this.drzava = drzava;
        this.kontinent = kontinent;
        this.slika = slika;
    }

     */
}
