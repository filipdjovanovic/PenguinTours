package com.penguins.project.model.Reservation;


import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Person.Person;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Reservation {

    @Id
    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence")
    private Long id;

    private Integer numberOfAdults;
    private Integer numberOfKids;
    private String paymentMethod;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    private Person personWhoReserved;

    @ManyToOne
    private Arrangement arrangement;

}
