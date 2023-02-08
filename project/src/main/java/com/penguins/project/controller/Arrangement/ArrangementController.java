package com.penguins.project.controller.Arrangement;

import com.penguins.project.model.Arrangement.Arrangement;

import com.penguins.project.model.Arrangement.ArrangementRepository;
import com.penguins.project.service.ArrangementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;



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

    @GetMapping(value = "/all")
    public Page<ArrangementShortW> getAllArrangements(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size) {

        if (page == null) page = 0;
        if (size == null) size = 50;

        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.getAllArrangements(pageable);
    }


    @GetMapping(value = "/get", params = {"id"})
    @ResponseBody
    public ArrangementW getArrangementById(@RequestParam Long id) {
        Arrangement arrangement = arrangementService.getArrangementById(id);
        if (arrangement == null){
            throw new IllegalStateException("Arrangement with id " + id + " does not exists");
        }
        return new ArrangementW(arrangement);

    }

    @PutMapping(value = "/update", params={"id"})
    public void updateArrangement(@RequestParam Long id, @RequestBody ArrangementParam arrangementParam){
        System.out.println(arrangementParam);
        arrangementService.updateArrangement(id,arrangementParam);
    }

    @GetMapping(value = "/get")
    @ResponseBody
    public Page<ArrangementShortW> getArrangements(@RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false) Integer size,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String city,
                                                   @RequestParam(required = false) String country,
                                                   @RequestParam(required = false) String continent,
                                                   @RequestParam(required = false) Date startDate,
                                                   @RequestParam(required = false) Date endDate){


        if (page == null) page = 0;
        if (size == null) size = 50;
        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.getArrangements(name,city,country,continent,startDate,endDate,pageable);

    }
}
