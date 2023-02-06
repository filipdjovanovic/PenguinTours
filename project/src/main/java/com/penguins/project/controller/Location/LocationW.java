package com.penguins.project.controller.Location;


import com.penguins.project.model.Location.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LocationW {
    private String city;
    private String country;
    private String continent;

    public LocationW(Location location){
        this.city = location.getCity();
        this.country = location.getCountry();
        this.continent = location.getContinent();
    }


}
