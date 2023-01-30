package com.penguins.project.controller.Arrangement;

import com.penguins.project.model.Arrangement.Arrangement;

import com.penguins.project.service.ArrangementService;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RequestMapping(path = "/arrangements")
@RestController
public class ArrangementController {
    private final ArrangementService arrangementService;

    public ArrangementController(ArrangementService arrangementService) {
        this.arrangementService = arrangementService;
    }

    //To get one all arrangements
    @GetMapping(value = "/all")
    public List<ArrangementW> getAllArrangements() {
        return arrangementService.getAllArrangements().stream().map(ArrangementW::new).toList();
    }


    //Da get one arrangement by id
    @GetMapping(value = "/get")
    @ResponseBody
    public ArrangementW getArrangement(@RequestParam Long id) {
        Arrangement arrangement = arrangementService.getArrangementById(id);
        return new ArrangementW(arrangement);

    }

    /*
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ArrangementW getArrangement(@PathVariable("id") Long id) {
        Arrangement arrangement = arrangementService.getArrangementById(id);
        return new ArrangementW(arrangement);


    }
     */


    //To add one arrangement
    @PostMapping(path = "/add")
    public void addArrangement(@RequestBody ArrangementParam arrangementParam){
        Arrangement arrangement = arrangementParam.toArrangement();
        arrangementService.addArrangement(arrangement);

    }

    @PutMapping(path = "/update/{facultyId}")
    public void updateArrangement(@RequestBody ArrangementParam arrangementParam) {
        Arrangement arrangement = arrangementParam.toArrangement();
        Arrangement arrangement2 = arrangementService.getArrangementById(arrangement.getId());

        if (!arrangement.getName().equalsIgnoreCase(arrangement2.getName())){

        }
        if (arrangement.getPrice() != arrangement2.getPrice()){

        }
        if (arrangement.getTransportation().equalsIgnoreCase(arrangement2.getTransportation())){

        }
        if (arrangement.getStatus().equalsIgnoreCase(arrangement2.getStatus())){

        }
        if (arrangement.getRemark().equalsIgnoreCase(arrangement2.getRemark())){

        }



    }
}
