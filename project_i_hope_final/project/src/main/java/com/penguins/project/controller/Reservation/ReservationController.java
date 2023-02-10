package com.penguins.project.controller.Reservation;


import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Person.Person;
import com.penguins.project.model.Reservation.Reservation;
import com.penguins.project.service.ArrangementService;
import com.penguins.project.service.PersonService;
import com.penguins.project.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    private final ReservationService reservationService;
    private final ArrangementService arrangementService;
    private final PersonService personService;

    public ReservationController(ReservationService reservationService,ArrangementService arrangementService,PersonService personService) {
        this.reservationService = reservationService;
        this.arrangementService = arrangementService;
        this.personService = personService;
    }

    @PostMapping(path = "/reservation/add", params={"arrangement_id"})
    public void addReservation(@RequestParam Long arrangement_id, @RequestBody ReservationParam reservationParam){
        Reservation reservation = reservationParam.toReservation();
        Arrangement arrangement = arrangementService.getArrangementById(arrangement_id);
        if (arrangement == null){
            throw new IllegalStateException("Arrangement with id " + arrangement_id + " does not exists");
        }

        reservation.setArrangement(arrangement);
        Person person = reservation.getPerson();
        List<Person> persons = personService
                .findByNameAndLastNameAndEmailAndContactAndAddress(person.getName(),person.getLastName(),person.getEmail(),person.getContact(),person.getAddress());
        if (persons.isEmpty()){
            reservation.setPerson(person);
        }else{
            reservation.setPerson(persons.get(0));
        }
        reservationService.addReservation(reservation);
    }

    @GetMapping(value = "/reservation/all")
    @ResponseBody
    public List<ReservationW> getAllReservations(){
        return reservationService.getAllReservations();
    }

    @PutMapping(value = "/reservation/update")
    public void updateReservation(@RequestParam Long id, @RequestParam String accepted){
        reservationService.updateReservation(id,accepted);
    }
}
