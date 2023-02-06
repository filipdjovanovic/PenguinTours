package com.penguins.project.controller.Arrangement;

import com.penguins.project.model.Arrangement.Arrangement;

import com.penguins.project.service.ArrangementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;


@RequestMapping(path = "/arrangements")
@RestController
public class ArrangementController {
    private final ArrangementService arrangementService;

    public ArrangementController(ArrangementService arrangementService) {
        this.arrangementService = arrangementService;
    }


    @PostMapping(path = "/add")
    public void addArrangement(@RequestBody ArrangementParam arrangementParam){
        Arrangement arrangement = arrangementParam.toArrangement();
        arrangementService.addArrangement(arrangement);

    }

    @GetMapping(value = "/all", params={"page","size"})
    public Page<ArrangementShortW> getAllArrangements(@RequestParam Integer page, @RequestParam Integer size) {

        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.getAllArrangements(pageable);
    }


    @GetMapping(value = "/get", params = {"id"})
    @ResponseBody
    public ArrangementW getArrangementById(@RequestParam Long id) {
        Arrangement arrangement = arrangementService.getArrangementById(id);
        return new ArrangementW(arrangement);

    }
    @GetMapping(value = "/get", params= {"page","size","city"})
    @ResponseBody
    public List<ArrangementShortW> getArrangementByCity(@RequestParam Integer page,@RequestParam Integer size,@RequestParam String city) {
        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.findArrangementByCity(city,pageable);

    }
    @GetMapping(value = "/get", params= {"page","size","country"})
    @ResponseBody
    public List<ArrangementShortW> getArrangementByCountry(@RequestParam Integer page,@RequestParam Integer size,@RequestParam String country) {
        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.findArrangementByCountry(country,pageable);
    }

    @GetMapping(value = "/get", params= {"page","size","continent"})
    @ResponseBody
    public List<ArrangementShortW> getArrangementByContinent(@RequestParam Integer page,@RequestParam Integer size,@RequestParam String continent) {
        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.findArrangementByContinent(continent,pageable);
    }

    @GetMapping(value = "/get", params= {"page","size","startDate"})
    @ResponseBody
    public List<ArrangementShortW> getArrangementByStartDate(@RequestParam Integer page,@RequestParam Integer size,@RequestParam Date startDate) {
        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.findArrangementByStartDate(startDate,pageable);
    }
    @GetMapping(value = "/get", params= {"page","size","startDate", "endDate"})
    @ResponseBody
    public List<ArrangementShortW> getArrangementByStartDate(@RequestParam Integer page,@RequestParam Integer size,@RequestParam Date startDate,@RequestParam Date endDate) {
        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.findArrangementByStartDateAndEndDate(startDate, endDate,pageable);
    }

    @GetMapping(value = "/get", params= {"page","size","name"})
    @ResponseBody
    public List<ArrangementShortW> getArrangementByName(@RequestParam Integer page,@RequestParam Integer size,  @RequestParam String name) {
        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.findArrangementByName(name,pageable);
    }

    @DeleteMapping(value = "/delete", params={"id"})
    public void deleteArrangement(@RequestParam Long id){
        arrangementService.deleteArrangement(id);
    }

    @PutMapping(value = "/update", params={"id"})
    public void updateArrangement(@RequestParam Long id, @RequestBody ArrangementParam arrangementParam){
        System.out.println(arrangementParam);
        arrangementService.updateArrangement(id,arrangementParam);
    }










    /*
    @GetMapping(value = "/all")
    public List<ArrangementShortW> getAllArrangements() {
         Map<Arrangement, List<Location>> arrangementListLocationMap =
                 arrangementService.getAllArrangements().stream()
                         .collect(Collectors.toMap(arrangement -> arrangement, arrangement -> arrangement.getPrograms()
                                         .stream()
                                         .map(program -> program.getLocations()
                                                 .stream()
                                                 .toList())
                                         .flatMap(Collection::stream)
                                         .collect(Collectors.toList()))
                         )



    }

     */

    /*
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ArrangementW getArrangement(@PathVariable("id") Long id) {
        Arrangement arrangement = arrangementService.getArrangementById(id);
        return new ArrangementW(arrangement);


    }
     */



    /*
    @PutMapping(path = "/update/{arrangementId}")
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


     */

}
