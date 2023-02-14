package com.penguins.project.service;

import com.penguins.project.controller.Arrangement.ArrangementParam;
import com.penguins.project.controller.Arrangement.ArrangementShortW;
import com.penguins.project.model.Accomodation.Accomodation;
import com.penguins.project.model.Accomodation.AccomodationRepository;
import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Arrangement.ArrangementRepository;
import com.penguins.project.model.Location.Location;
import com.penguins.project.model.Location.LocationRepository;
import com.penguins.project.model.Program.Program;
import com.penguins.project.model.Program.ProgramRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArrangementService {

    private final ArrangementRepository arrangementRepository;

    private final LocationService locationService;
    private final AccomodationRepository accomodationRepository;

    private final AccomodationService accomodationService;
    private final ProgramRepository programRepository;
    private final LocationRepository locationRepository;

    public ArrangementService(ArrangementRepository arrangementRepository,
                              LocationService locationService,
                              AccomodationRepository accomodationRepository,
                              AccomodationService accomodationService,
                              ProgramRepository programRepository,
                              LocationRepository locationRepository) {
        this.arrangementRepository = arrangementRepository;
        this.locationService = locationService;
        this.accomodationRepository = accomodationRepository;
        this.accomodationService = accomodationService;
        this.programRepository = programRepository;
        this.locationRepository = locationRepository;
    }


    @Transactional
    public void addArrangement(Arrangement arrangement){

        if (!findArrangementByName(arrangement.getName(),Pageable.unpaged()).isEmpty()){
            throw new IllegalStateException("Name " + arrangement.getName() + " of arrangement already exists");
        }

        arrangement.setPrograms(arrangement.getPrograms()
                .stream()
                .peek(program -> program.setLocations(program.getLocations()
                        .stream()
                        .map(location -> {
                            List<Location> list = locationService.findSame(location);
                            if(!list.isEmpty()){
                                return list.get(0);
                            }
                            byte[] imageBytes = Base64.getDecoder().decode(location.getPicture().substring(23));
                            try {
                                FileOutputStream fos = new FileOutputStream("images/" + location + ".jpg");
                                fos.write(imageBytes);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            location.setPicture("images/" + location + ".jpg");
                            locationRepository.save(location);
                            return location;
                        })
                        .collect(Collectors.toSet())))
                .collect(Collectors.toSet()));

        arrangement.setAccomodations(arrangement.getAccomodations()
                .stream()
                .peek(accomodation -> {

                    byte[] imageBytes;
                    Random rand = new Random();
                    if (accomodation.getPicture1()!=null){
                        imageBytes = Base64.getDecoder().decode(accomodation.getPicture1().substring(23));
                        try {
                            String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                            FileOutputStream fos = new FileOutputStream(name);
                            fos.write(imageBytes);
                            accomodation.setPicture1(name);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (accomodation.getPicture2()!=null){
                        imageBytes = Base64.getDecoder().decode(accomodation.getPicture2().substring(23));
                        try {
                            String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                            FileOutputStream fos = new FileOutputStream(name);
                            fos.write(imageBytes);
                            accomodation.setPicture2(name);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (accomodation.getPicture3()!=null){
                        imageBytes = Base64.getDecoder().decode(accomodation.getPicture3().substring(23));
                        try {
                            String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                            FileOutputStream fos = new FileOutputStream(name);
                            fos.write(imageBytes);
                            accomodation.setPicture3(name);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (accomodation.getPicture4()!=null){
                        imageBytes = Base64.getDecoder().decode(accomodation.getPicture4().substring(23));
                        try {
                            String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                            FileOutputStream fos = new FileOutputStream(name);
                            fos.write(imageBytes);
                            accomodation.setPicture4(name);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (accomodation.getPicture5()!=null){
                        imageBytes = Base64.getDecoder().decode(accomodation.getPicture5().substring(23));
                        try {
                            String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                            FileOutputStream fos = new FileOutputStream(name);
                            fos.write(imageBytes);
                            accomodation.setPicture5(name);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (accomodation.getPicture6()!=null){
                        imageBytes = Base64.getDecoder().decode(accomodation.getPicture6().substring(23));
                        try {
                            String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                            FileOutputStream fos = new FileOutputStream(name);
                            fos.write(imageBytes);
                            accomodation.setPicture6(name);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                })
                .collect(Collectors.toSet()));

        arrangement.setAccomodations(arrangement.getAccomodations()
                .stream()
                .map(accomodation -> {
                    List<Accomodation> list = accomodationService.findSame(accomodation);
                    if(list.isEmpty()){
                        return accomodation;
                    }else{
                        return list.get(0);
                    }
                })
                .collect(Collectors.toSet()));


        arrangementRepository.save(arrangement);
    }

    @Transactional
    public synchronized void saveArrangement(Arrangement arrangement){


        arrangement.setPrograms(arrangement.getPrograms()
                .stream()
                .peek(program -> program.setLocations(program.getLocations()
                        .stream()
                        .map(location -> {
                            List<Location> list = locationService.findSame(location);
                            if(!list.isEmpty()){
                                return list.get(0);
                            }
                            locationRepository.save(location);
                            return location;

                        })
                        .collect(Collectors.toSet())))
                .collect(Collectors.toSet()));

        arrangementRepository.save(arrangement);
    }

    @Transactional
    public Arrangement getArrangementById(Long id){
        return arrangementRepository.getArrangementById(id);
    }

    @Transactional
    public Page<ArrangementShortW> getAllArrangements(Pageable pageable){
        Page<Object[]> page = arrangementRepository.findAllArrangements(pageable);

        return new PageImpl<>(
                page.getContent()
                        .stream()
                        .map(obj -> {
                            ArrangementShortW arrangementShortW =new ArrangementShortW(obj);
                            Location location = Location.builder()
                                    .city(arrangementShortW.getCity())
                                    .country(arrangementShortW.getCountry())
                                    .continent(arrangementShortW.getContinent())
                                    .build();
                            Location locationInBase = locationService.findSame(location).get(0);
                            try {
                                arrangementShortW.setBigPicture(Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(locationInBase.getPicture()))));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            return arrangementShortW;
                            }
                        )
                        .toList()
                ,pageable
                ,page.getTotalElements());

    }

    @Transactional
    public List<ArrangementShortW> findArrangementByName(String name, Pageable pageable){
        List<ArrangementShortW> list = arrangementRepository.findArrangementByName(name,pageable)
                .stream()
                .map(obj -> new ArrangementShortW(obj)
                )
                .toList();
        return list;
    }

    @Transactional
    public void deleteArrangement(Long arrangementId){
        boolean exists = arrangementRepository.existsById(arrangementId);
        if (!exists){
            throw new IllegalStateException("Arrangement with id " + arrangementId + " does not exists");
        }
        arrangementRepository.deleteById(arrangementId);
    }

    @Transactional
    public void updateArrangement(Long id, ArrangementParam arrangementParam){
        Arrangement arrangement= arrangementRepository.findById(id)
               .orElseThrow(() -> new IllegalStateException("arrangement with id " + id + " does not exist"));

        if (!arrangementParam.getName().equals(arrangement.getName()) && !findArrangementByName(arrangementParam.getName(),Pageable.unpaged()).isEmpty()){
            throw new IllegalStateException("Name " + arrangementParam.getName() + " of arrangement already exists");
        }

        if (arrangementParam.getName() != null && !arrangement.getName().equals(arrangementParam.getName())){
            arrangement.setName(arrangementParam.getName());
        }
        if (arrangementParam.getPrice() != null && !arrangement.getPrice().equals(arrangementParam.getPrice())){
            arrangement.setPrice(arrangementParam.getPrice());
        }
        if (arrangementParam.getTransportation() != null && !arrangement.getTransportation().equals(arrangementParam.getTransportation())){
            arrangement.setTransportation(arrangementParam.getTransportation());
        }
        if (arrangementParam.getStatus() != null && !arrangement.getStatus().equals(arrangementParam.getStatus())){
            arrangement.setStatus(arrangementParam.getStatus());
        }
        if (arrangementParam.getRemark() != null && !arrangement.getRemark().equals(arrangementParam.getRemark())){
            arrangement.setRemark(arrangementParam.getRemark());
        }

        Arrangement updatedArrangement = arrangementParam.toArrangement();


        Set<Program> programs = updatedArrangement.getPrograms();
        if (programs != null){
            programRepository.deleteByArrangement(arrangementRepository.getReferenceById(id));

            arrangement.setPrograms(programs
                    .stream()
                    .peek(program -> program.setLocations(program.getLocations()
                            .stream()
                            .map(location -> {
                                List<Location> list = locationService.findSame(location);
                                if(!list.isEmpty()){
                                    return list.get(0);
                                }
                                byte[] imageBytes = Base64.getDecoder().decode(location.getPicture());
                                try {
                                    FileOutputStream fos = new FileOutputStream("images/" + location + ".jpg");
                                    fos.write(imageBytes);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                location.setPicture("images/" + location + ".jpg");
                                locationRepository.save(location);
                                return location;
                            })
                            .collect(Collectors.toSet())))
                    .collect(Collectors.toSet()));
        }

        Set<Accomodation> accomodations = updatedArrangement.getAccomodations();
        if (accomodations != null){


            accomodations = accomodations
                    .stream()
                    .peek(accomodation -> {


                        Random rand = new Random();
                        if (accomodation.getPicture1()!=null){
                            byte[] imageBytes1 = Base64.getDecoder().decode(accomodation.getPicture1());
                            try {
                                String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                                FileOutputStream fos = new FileOutputStream(name);
                                fos.write(imageBytes1);
                                accomodation.setPicture1(name);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (accomodation.getPicture2()!=null){
                            byte[] imageBytes2 = Base64.getDecoder().decode(accomodation.getPicture2());
                            try {
                                String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                                FileOutputStream fos = new FileOutputStream(name);
                                fos.write(imageBytes2);
                                accomodation.setPicture2(name);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (accomodation.getPicture3()!=null){
                            byte[] imageBytes3 = Base64.getDecoder().decode(accomodation.getPicture3());
                            try {
                                String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                                FileOutputStream fos = new FileOutputStream(name);
                                fos.write(imageBytes3);
                                accomodation.setPicture3(name);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (accomodation.getPicture4()!=null){
                            byte[] imageBytes4 = Base64.getDecoder().decode(accomodation.getPicture4());
                            try {
                                String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                                FileOutputStream fos = new FileOutputStream(name);
                                fos.write(imageBytes4);
                                accomodation.setPicture4(name);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (accomodation.getPicture5()!=null){
                            byte[] imageBytes5 = Base64.getDecoder().decode(accomodation.getPicture5());
                            try {
                                String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                                FileOutputStream fos = new FileOutputStream(name);
                                fos.write(imageBytes5);
                                accomodation.setPicture5(name);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (accomodation.getPicture6()!=null){
                            byte[] imageBytes6 = Base64.getDecoder().decode(accomodation.getPicture6());
                            try {
                                String name = "images/" + accomodation.getName() + rand.nextInt(10000) + ".jpg";
                                FileOutputStream fos = new FileOutputStream(name);
                                fos.write(imageBytes6);
                                accomodation.setPicture6(name);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    })
                    .collect(Collectors.toSet());

            arrangement.setAccomodations(accomodations
                    .stream()
                    .map(accomodation -> {
                        List<Accomodation> list = accomodationService.findSame(accomodation);
                        if(list.isEmpty()){
                            return accomodation;
                        }else{
                            return list.get(0);
                        }
                    })
                    .collect(Collectors.toSet()));


        }

        arrangementRepository.save(arrangement);


    }




    @Transactional
    public Page<ArrangementShortW> getArrangements(String name, String city,String country,String continent,String transportation,Date startDate,Date endDate, Pageable pageable) {

        Page<ArrangementShortW> page = this.getAllArrangements(Pageable.unpaged());
        List<ArrangementShortW> allArrangements = page.getContent();

        if (name != null) {
            allArrangements = allArrangements
                    .stream()
                    .filter(arrangementShortW -> arrangementShortW.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }
        if (transportation != null) {
            allArrangements = allArrangements
                    .stream()
                    .filter(arrangementShortW -> arrangementShortW.getTransportation().equals(transportation))
                    .toList();
        }
        if (startDate != null && endDate != null){
            allArrangements = allArrangements
                    .stream()
                    .filter(arrangementShortW -> arrangementShortW.getStartDate().compareTo(startDate)>=0)
                    .toList();
            allArrangements = allArrangements
                    .stream()
                    .filter(arrangementShortW -> arrangementShortW.getEndDate().compareTo(endDate)<=0)
                    .toList();
        }
        else if (startDate != null) {
            allArrangements = allArrangements
                    .stream()
                    .filter(arrangementShortW -> arrangementShortW.getStartDate().equals(startDate))
                    .toList();
        }

        if (city != null){
            allArrangements = allArrangements
                    .stream()
                    .filter(arrangementShortW -> arrangementRepository
                            .getArrangementById(arrangementShortW.getId())
                            .getPrograms()
                                .stream()
                                .anyMatch(program -> program
                                        .getLocations()
                                        .stream()
                                        .anyMatch(location -> location
                                                .getCity()
                                                .equalsIgnoreCase(city)
                                        )
                                )
                    )
                    .toList();
        }
        if (country != null){
            allArrangements = allArrangements
                    .stream()
                    .filter(arrangementShortW -> arrangementRepository
                            .getArrangementById(arrangementShortW.getId())
                            .getPrograms()
                            .stream()
                            .anyMatch(program -> program
                                    .getLocations()
                                    .stream()
                                    .anyMatch(location -> location
                                            .getCountry()
                                            .equalsIgnoreCase(country)
                                    )
                            )
                    )
                    .toList();
        }
        if (continent != null){
            allArrangements = allArrangements
                    .stream()
                    .filter(arrangementShortW -> arrangementRepository
                            .getArrangementById(arrangementShortW.getId())
                            .getPrograms()
                            .stream()
                            .anyMatch(program -> program
                                    .getLocations()
                                    .stream()
                                    .anyMatch(location -> location
                                            .getContinent()
                                            .equalsIgnoreCase(continent)
                                    )
                            )
                    )
                    .toList();
        }
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), allArrangements.size());
        return new PageImpl<>(
                allArrangements.subList(start,end)
                ,pageable
                ,allArrangements.size());

    }

    @Transactional
    public List<ArrangementShortW> getTopArrangements(){
        List<ArrangementShortW> list = arrangementRepository.findTopArrangements()
                .stream()
                .map(obj -> {
                        ArrangementShortW arrangementShortW =new ArrangementShortW(obj);
                        Location location = Location.builder()
                                .city(arrangementShortW.getCity())
                                .country(arrangementShortW.getCountry())
                                .continent(arrangementShortW.getContinent())
                                .build();
                        Location locationInBase = locationService.findSame(location).get(0);
                        try {
                            arrangementShortW.setBigPicture(Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(locationInBase.getPicture()))));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return arrangementShortW;
                    }
                )
                .toList();
        return list;
    }
}
