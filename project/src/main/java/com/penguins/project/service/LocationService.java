package com.penguins.project.service;

import com.penguins.project.model.Location.Location;
import com.penguins.project.model.Location.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Transactional
    public List<Location> findSame(Location location){
        Example<Location> example = Example.of(location);
        return locationRepository.findAll(example);

    }
}
