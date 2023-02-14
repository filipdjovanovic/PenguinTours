package com.penguins.project.controller.Location;


import com.penguins.project.model.Location.Location;
import com.penguins.project.service.LocationService;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class LocationParam {

    private String city;
    private String country;
    private String continent;
    private String picture;

    public Location toLocation(){
        Location location = Location.builder()
                .city(this.getCity())
                .country(this.getCountry())
                .continent(this.getContinent())
                .picture(this.picture)
                .build();

        return location;
    }


}
