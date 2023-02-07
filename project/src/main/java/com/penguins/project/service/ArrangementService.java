package com.penguins.project.service;

import com.penguins.project.controller.Arrangement.ArrangementParam;
import com.penguins.project.controller.Arrangement.ArrangementShortW;
import com.penguins.project.model.Accomodation.Accomodation;
import com.penguins.project.model.Accomodation.AccomodationRepository;
import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Arrangement.ArrangementRepository;
import com.penguins.project.model.Location.Location;
import com.penguins.project.model.Program.Program;
import com.penguins.project.model.Program.ProgramRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArrangementService {

    private final ArrangementRepository arrangementRepository;

    private final LocationService locationService;
    private final AccomodationRepository accomodationRepository;

    private final AccomodationService accomodationService;
    private final ProgramRepository programRepository;

    public ArrangementService(ArrangementRepository arrangementRepository,
                              LocationService locationService,
                              AccomodationRepository accomodationRepository,
                              AccomodationService accomodationService,
                              ProgramRepository programRepository) {
        this.arrangementRepository = arrangementRepository;
        this.locationService = locationService;
        this.accomodationRepository = accomodationRepository;
        this.accomodationService = accomodationService;
        this.programRepository = programRepository;
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
                            return location;
                        })
                        .collect(Collectors.toSet())))
                .collect(Collectors.toSet()));


        arrangement.setAccomodations(arrangement.getAccomodations()
                .stream()
                .peek(accomodation -> {
                    Location location = accomodation.getLocation();
                    List<Location> list = locationService.findSame(location);
                    if(!list.isEmpty()){
                        accomodation.setLocation(list.get(0));
                    }else{
                        accomodation.setLocation(location);
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
    public Arrangement getArrangementById(Long id){
        return arrangementRepository.getArrangementById(id);
    }

    @Transactional
    public Page<ArrangementShortW> getAllArrangements(Pageable pageable){
        Page<Object[]> page = arrangementRepository.findAllArrangements(pageable);

        return new PageImpl<>(
                page.getContent()
                        .stream()
                        .map(obj -> new ArrangementShortW(obj)
                        )
                        .toList()
                ,pageable
                ,page.getTotalElements());

    }

    @Transactional
    public List<ArrangementShortW> findArrangementByCity(String city,Pageable pageable){
        List<ArrangementShortW> list = arrangementRepository.findArrangementByCity(city,pageable)
                .stream()
                .map(obj -> new ArrangementShortW(obj)
                )
                .toList();
        return list;
     }

    @Transactional
    public List<ArrangementShortW> findArrangementByCountry(String country,Pageable pageable){
        List<ArrangementShortW> list = arrangementRepository.findArrangementByCountry(country,pageable)
                .stream()
                .map(obj -> new ArrangementShortW(obj)
                )
                .toList();
        return list;
    }

    @Transactional
    public List<ArrangementShortW> findArrangementByContinent(String continent,Pageable pageable){
        List<ArrangementShortW> list = arrangementRepository.findArrangementByContinent(continent,pageable)
                .stream()
                .map(obj -> new ArrangementShortW(obj)
                )
                .toList();
        return list;
    }

    @Transactional
    public List<ArrangementShortW> findArrangementByStartDate(Date startDate,Pageable pageable){
        List<ArrangementShortW> list = arrangementRepository.findArrangementByStartDate(startDate,pageable)
                .stream()
                .map(obj -> new ArrangementShortW(obj)
                )
                .toList();
        return list;
    }
    @Transactional
    public List<ArrangementShortW> findArrangementByStartDateAndEndDate(Date startDate,Date endDate,Pageable pageable){
        List<ArrangementShortW> list = arrangementRepository.findArrangementByStartDateAndEndDate(startDate,endDate,pageable)
                .stream()
                .map(obj -> new ArrangementShortW(obj)
                )
                .toList();
        return list;
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
        //programRepository.deleteProgramByArrangementId(arrangementId);
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
                                return location;
                            })
                            .collect(Collectors.toSet())))
                    .collect(Collectors.toSet()));
        }

        Set<Accomodation> accomodations = updatedArrangement.getAccomodations();
        if (accomodations != null){
            //accomodationRepository.deleteByArragements(arrangementRepository.getReferenceById(id));

            accomodations = accomodations
                    .stream()
                    .peek(accomodation -> {
                        Location location = accomodation.getLocation();
                        List<Location> list = locationService.findSame(location);
                        if(!list.isEmpty()){
                            accomodation.setLocation(list.get(0));
                        }else{
                            accomodation.setLocation(location);
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
    public Page<ArrangementShortW> getArrangements(String name, String city,String country,String continent,Date startDate,Date endDate, Pageable pageable) {

        Page<ArrangementShortW> page = this.getAllArrangements(pageable);
        List<ArrangementShortW> allArrangements = page.getContent();

        if (name != null) {
            allArrangements = allArrangements
                    .stream()
                    .filter(arrangementShortW -> arrangementShortW.getName().equals(name))
                    .toList();
        }
        if (startDate != null) {
            allArrangements = allArrangements
                    .stream()
                    .filter(arrangementShortW -> arrangementShortW.getStartDate().equals(startDate))
                    .toList();
        }
        if (endDate != null) {
            allArrangements = allArrangements
                    .stream()
                    .filter(arrangementShortW -> arrangementShortW.getEndDate().equals(endDate))
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
                                                .equals(city)
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
                                            .equals(country)
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
                                            .equals(country)
                                    )
                            )
                    )
                    .toList();
        }

        return new PageImpl<>(
                allArrangements
                ,pageable
                ,page.getTotalElements());

    }
}
