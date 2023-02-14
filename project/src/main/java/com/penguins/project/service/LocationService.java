package com.penguins.project.service;

import com.penguins.project.model.Location.Location;
import com.penguins.project.model.Location.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Transactional
    public List<Location> findSame(Location location){
        String city = location.getCity();
        String country = location.getCountry();
        String continent = location.getContinent();
        return locationRepository.findByCityAndCountryAndContinent(city,country,continent);

    }

}
