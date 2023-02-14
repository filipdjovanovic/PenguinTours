package com.penguins.project.controller.Arrangement;

import com.penguins.project.Security.Flag;
import com.penguins.project.controller.Program.ProgramW;
import com.penguins.project.model.Arrangement.Arrangement;


import com.penguins.project.model.Location.Location;
import com.penguins.project.service.ArrangementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.penguins.project.populate.MyRunner.flag;
import static com.penguins.project.populate.Populator.getMostFrequentLocation;


@RequestMapping(path = "/arrangements")
@RestController
@CrossOrigin(value = "http://localhost:3000",allowedHeaders = {"Authorization"})
public class ArrangementController {
    private final ArrangementService arrangementService;


    public ArrangementController(ArrangementService arrangementService ) {
        this.arrangementService = arrangementService;
    }

    @GetMapping(path = "/flag")
    public Flag getFlag(){
        if (flag){
            return new Flag("true");
        }
        else{
            return new Flag("false");
        }

    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addArrangement(@RequestBody ArrangementParam arrangementParam){
        try{
            Arrangement arrangement = arrangementParam.toArrangement();
            arrangementService.addArrangement(arrangement);
        }catch(Exception e){
            return new ResponseEntity<>("Greka pri dodavanju aranžmana", HttpStatus.OK);
        }

        return new ResponseEntity<>("Aranzman je dodat!", HttpStatus.OK);

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
    public ArrangementW getArrangementById(@RequestParam Long id) throws IOException {
        Arrangement arrangement = arrangementService.getArrangementById(id);
        if (arrangement == null){
            throw new IllegalStateException("Arrangement with id " + id + " does not exists");
        }
        ArrangementW arrangementW = new ArrangementW(arrangement);
        List<ProgramW> programs = arrangementW.getPrograms();
        programs.sort(Comparator.comparing(ProgramW::getDate));

        arrangementW.setPrograms(programs);
        List<Location> listOfLocationsInArrangement;
        listOfLocationsInArrangement = arrangement.getPrograms()
                .stream()
                .map(program -> program.getLocations())
                .flatMap(set -> set.stream())
                .collect(Collectors.toList());
        Location mostFrequentLocation = getMostFrequentLocation(listOfLocationsInArrangement);

        try {
            String bigPicture = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(mostFrequentLocation.getPicture())));
            arrangementW.setBigPicture(bigPicture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return arrangementW;

    }

    @PutMapping(value = "/update", params={"id"})
    public ResponseEntity<String> updateArrangement(@RequestParam Long id, @RequestBody(required = false) ArrangementParam arrangementParam){
        try{
            arrangementService.updateArrangement(id,arrangementParam);
        }catch(Exception e){
            return new ResponseEntity<>("Greška pri ažuriranju aranžmana!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Aranžman je uspešno ažuriran!", HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    @ResponseBody
    public Page<ArrangementShortW> getArrangements(@RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false) Integer size,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String city,
                                                   @RequestParam(required = false) String country,
                                                   @RequestParam(required = false) String continent,
                                                   @RequestParam(required = false) String transportation,
                                                   @RequestParam(required = false) Date startDate,
                                                   @RequestParam(required = false) Date endDate){


        if (page == null) page = 0;
        if (size == null) size = 50;
        Pageable pageable = PageRequest.of(page,size);
        return arrangementService.getArrangements(name,city,country,continent,transportation,startDate,endDate,pageable);

    }

    @DeleteMapping(value = "/delete", params={"id"})
    public void deleteArrangement(@RequestParam Long id){
        arrangementService.deleteArrangement(id);
    }

    @GetMapping(value = "/hot")
    public List<ArrangementShortW> getTopArrangements() {
        return arrangementService.getTopArrangements();
    }





}
