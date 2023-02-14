package com.penguins.project.controller.Location;


import com.penguins.project.model.Location.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Getter
@Builder
@AllArgsConstructor
public class LocationW {
    private String city;
    private String country;
    private String continent;

    private String picture;
    public LocationW(Location location) throws IOException {
        this.city = location.getCity();
        this.country = location.getCountry();
        this.continent = location.getContinent();
        this.picture = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(location.getPicture())));

    }


}
