package com.penguins.project.service;

import com.penguins.project.controller.Reservation.ReservationW;
import com.penguins.project.model.Reservation.Reservation;
import com.penguins.project.model.Reservation.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @Transactional
    public List<ReservationW> getAllReservations(String arrangementName){
        List<ReservationW> reservationList = reservationRepository.findByOrderByAcceptedAsc()
                .stream()
                .map(reservation -> new ReservationW(reservation))
                .toList();
        if (arrangementName != null){
            reservationList = reservationList
                    .stream()
                    .filter(reservationW -> reservationW.getArrangementName().contains(arrangementName))
                    .toList();
        }
        return reservationList;
    }
    public void updateReservation(Long id,String accepted){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Reservation with id " + id + "does not exist"));

        reservation.setAccepted(accepted);
        reservationRepository.save(reservation);

    }


}
