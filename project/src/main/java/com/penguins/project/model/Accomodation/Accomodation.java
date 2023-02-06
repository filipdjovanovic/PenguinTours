package com.penguins.project.model.Accomodation;


import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Location.Location;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accomodation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Accomodation {
    @Id
    @SequenceGenerator(
            name = "accomodation_sequence",
            sequenceName = "accomodation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "accomodation_sequence")
    private Long id;

    private String name;
    private String category;
    private String type;
    private Boolean tv;
    private Boolean safe;
    private Boolean fridge;
    private Boolean ac;
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

    @ManyToMany(mappedBy = "accomodations")
    private Set<Arrangement> arrangements = new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;

    
}
