package com.penguins.project.controller.Reservation;


import com.penguins.project.controller.Arrangement.ArrangementShortW;
import com.penguins.project.controller.Person.PersonW;
import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Person.Person;
import com.penguins.project.model.Reservation.Reservation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReservationW {
    private Long id;
    private Integer numberOfAdults;
    private Integer numberOfKids;
    private String paymentMethod;
    private String comment;
    private String accepted;
    private PersonW person;
    private Long arrangementId;
    private String arrangementName;


    public ReservationW(Reservation reservation){
        this.id= reservation.getId();
        this.numberOfAdults = reservation.getNumberOfAdults();
        this.numberOfKids = reservation.getNumberOfKids();
        this.paymentMethod = reservation.getPaymentMethod();
        this.comment = reservation.getComment();
        this.accepted = reservation.getAccepted();
        this.person = new PersonW(reservation.getPerson());
        this.arrangementId = reservation.getArrangement().getId();
        this.arrangementName = reservation.getArrangement().getName();
    }
}
