package com.penguins.project.controller.Aranzman;


import com.penguins.project.model.Aranzman.Aranzman;
import com.penguins.project.model.Aranzman.AranzmanRepository;
import com.penguins.project.model.Lokacija.Lokacija;
import com.penguins.project.model.Program.Program;
import com.penguins.project.model.Smestaj.Smestaj;
import com.penguins.project.service.AranzmanService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@RequestMapping(path = "/aranzmani")
@RestController
public class AranzmanController {
    private final AranzmanService aranzmanService;
    private final AranzmanRepository aranzmanRepository;

    public AranzmanController(AranzmanService aranzmanService,
                              AranzmanRepository aranzmanRepository) {
        this.aranzmanService = aranzmanService;
        this.aranzmanRepository = aranzmanRepository;
    }


    //Da vratim sve aranzmane
    @GetMapping(value = "/svi")
    public List<Aranzman> getAllAranzmans() {
        return aranzmanService.getAllAranzmans();
    }


    //Da vratim samo jedan aranzman
    @GetMapping(value = "/{id}")
    public Optional<Aranzman> getAranzman(@RequestParam Long id){
            return aranzmanService.getAranzmanById(id);
    }

    //Da dodam jedan aranzman
    @PostMapping(path = "/dodaj")
    public void addAranzman(@RequestBody AranzmanParam aranzmanParam){
        Set<Smestaj> smestaji = aranzmanParam.getSmestaji()
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

        Set<Program> programi = aranzmanParam.getProgrami()
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

        Aranzman aranzman = Aranzman.builder()
                        .naziv(aranzmanParam.getNaziv())
                        .cena(aranzmanParam.getCena())
                        .prevoz(aranzmanParam.getPrevoz())
                        .status(aranzmanParam.getStatus())
                        .smestaji(smestaji)
                        .programi(programi)
                        .build();

        aranzmanService.addAranzman(aranzman);
    }
}
