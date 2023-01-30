package com.penguins.project.controller.Arrangement;

import com.penguins.project.controller.Program.ProgramParam;
import com.penguins.project.controller.Accomodation.AccomodationParam;
import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Location.Location;
import com.penguins.project.model.Program.Program;
import com.penguins.project.model.Accomodation.Accomodation;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ArrangementParam {
    private String name;
    private Integer price;
    private String transportation;
    private String status;

    //private Integer broj_dana;
    private String remark;
    private Set<ProgramParam> programs = new HashSet<>();
    private Set<AccomodationParam> accomodations = new HashSet<>();

    public Arrangement toArrangement(){
        Set<Accomodation> accomodations = this.getAccomodations()
                .stream()
                .map(accomodationParam -> Accomodation
                        .builder()
                        .name(accomodationParam.getName())
                        .category(accomodationParam.getCategory())
                        .type(accomodationParam.getType())
                        .tv(accomodationParam.getTv())
                        .safe(accomodationParam.getSafe())
                        .fridge(accomodationParam.getFridge())
                        .ac(accomodationParam.getAc())
                        .internet(accomodationParam.getInternet())
                        .build())
                .collect(Collectors.toSet());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        Set<Program> programs = this.getPrograms()
                .stream()
                .map(programParam -> Program
                        .builder()
                        .description(programParam.getDescription())
                        .date(LocalDate.parse(programParam.getDate(),formatter))
                        .locations(programParam.getLocations()
                                .stream()
                                .map(locationParam -> Location.builder()
                                        .city(locationParam.getCity())
                                        .country(locationParam.getCountry())
                                        .continent(locationParam.getContinent())
                                        .build())
                                .collect(Collectors.toSet()))
                        .build())
                .collect(Collectors.toSet());

        Arrangement arrangement = Arrangement.builder()
                .name(this.getName())
                .price(this.getPrice())
                .transportation(this.getTransportation())
                .status(this.getStatus())
                .remark(this.getRemark())
                .accomodations(accomodations)
                .programs(programs)
                .build();

        return arrangement;

        /*
        Set<Smestaj> smestaji = arrangementParam.getSmestaji()
                .stream()
                .map(smestajParam -> Smestaj
                        .builder()
                        .ime(smestajParam.getIme())
                        .kategorija(smestajParam.getKategorija())
                        .tip_smestaja(smestajParam.getTip_smestaja())
                        .tv(smestajParam.getTv())
                        .sef(smestajParam.getSef())
                        .frizider(smestajParam.getSef())
                        .klima(smestajParam.getKlima())
                        .internet(smestajParam.getInternet())
                        .build())
                .collect(Collectors.toSet());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        Set<Program> programi = arrangementParam.getProgrami()
                .stream()
                .map(programParam -> Program
                        .builder()
                        .opis(programParam.getOpis())
                        .datum(LocalDate.parse(programParam.getDatum(),formatter))
                        .lokacije(programParam.getLokacije()
                                .stream()
                                .map(lokacijaParam -> Lokacija.builder()
                                        .grad(lokacijaParam.getGrad())
                                        .drzava(lokacijaParam.getDrzava())
                                        .kontinent(lokacijaParam.getKontinent())
                                        .build())
                                .collect(Collectors.toSet()))
                        .build())
                .collect(Collectors.toSet());

        Arrangement arrangement = Arrangement.builder()
                        .naziv(arrangementParam.getNaziv())
                        .cena(arrangementParam.getCena())
                        .prevoz(arrangementParam.getPrevoz())
                        .status(arrangementParam.getStatus())
                        .napomena(arrangementParam.getNapomena())
                        .smestaji(smestaji)
                        .programi(programi)
                        .build();

         */
    }
}
