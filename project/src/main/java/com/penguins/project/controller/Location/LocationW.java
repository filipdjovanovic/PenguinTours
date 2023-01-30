package com.penguins.project.controller.Location;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LocationW {
    private String city;
    private String country;
    private String continent;
}
