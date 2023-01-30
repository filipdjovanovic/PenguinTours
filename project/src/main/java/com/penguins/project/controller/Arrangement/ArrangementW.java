package com.penguins.project.controller.Arrangement;

import com.penguins.project.controller.Location.LocationW;
import com.penguins.project.controller.Program.ProgramW;
import com.penguins.project.controller.Accomodation.AccomodationW;
import com.penguins.project.model.Arrangement.Arrangement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ArrangementW {
    private Long id;
    private String name;
    private Integer price;
    private String transportation;
    private String status;

    //private Integer broj_dana;
    private String remark;
    private Set<AccomodationW> accomodations = new HashSet<>();
    private Set<ProgramW> programs = new HashSet<>();


    public ArrangementW(Arrangement arrangement) {
        this.id = arrangement.getId();
        this.name = arrangement.getName();
        this.price = arrangement.getPrice();
        this.transportation = arrangement.getTransportation();
        this.status = arrangement.getStatus();
        this.remark = arrangement.getRemark();
        this.accomodations = arrangement.getAccomodations().
                stream()
                .map(Accomodation -> AccomodationW
                        .builder()
                        .name(Accomodation.getName())
                        .category(Accomodation.getCategory())
                        .type(Accomodation.getType())
                        .tv(Accomodation.getTv())
                        .safe(Accomodation.getSafe())
                        .fridge(Accomodation.getFridge())
                        .ac(Accomodation.getAc())
                        .internet(Accomodation.getInternet())
                        .build())
                .collect(Collectors.toSet());
        this.programs = arrangement.getPrograms()
                .stream()
                .map(Program -> ProgramW
                        .builder()
                        .description(Program.getDescription())
                        .date(Program.getDate().toString())
                        .locations(Program.getLocations()
                                .stream()
                                .map(Location -> LocationW.builder()
                                        .city(Location.getCity())
                                        .country(Location.getCountry())
                                        .continent(Location.getContinent())
                                        .build())
                                .collect(Collectors.toSet()))
                        .build())
                .collect(Collectors.toSet());
    }
}
