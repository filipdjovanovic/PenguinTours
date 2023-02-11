package com.penguins.project.controller.Reservation;


import com.penguins.project.controller.Arrangement.ArrangementParam;
import com.penguins.project.controller.Person.PersonParam;
import com.penguins.project.model.Accomodation.Accomodation;
import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Person.Person;
import com.penguins.project.model.Reservation.Reservation;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
public class ReservationParam {

    private Integer numberOfAdults;
    private Integer numberOfKids;
    private String paymentMethod;
    private String comment;
    private PersonParam person;


    public Reservation toReservation(){
        Reservation reservation = Reservation.builder()
                .numberOfAdults(this.numberOfAdults)
                .numberOfKids(this.numberOfKids)
                .paymentMethod(this.paymentMethod)
                .comment(this.comment)
                .person(this.person.toPerson())
                .build();
        return reservation;
    }
}
