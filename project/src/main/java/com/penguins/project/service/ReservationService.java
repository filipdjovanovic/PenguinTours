package com.penguins.project.service;

import com.penguins.project.model.Reservation.Reservation;
import com.penguins.project.model.Reservation.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;


    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public void addReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }
}
