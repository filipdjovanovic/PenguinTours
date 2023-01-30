package com.penguins.project.controller.Accomodation;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccomodationW {
    private String name;
    private String category;
    private String type;
    private Boolean tv;
    private Boolean safe;
    private Boolean fridge;
    private Boolean ac;
    private Boolean internet;
}
