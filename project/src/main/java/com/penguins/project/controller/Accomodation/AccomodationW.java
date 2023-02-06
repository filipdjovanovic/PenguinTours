package com.penguins.project.controller.Accomodation;


import com.penguins.project.controller.Location.LocationW;
import com.penguins.project.model.Accomodation.Accomodation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AccomodationW {
    private String name;
    private String category;
    private String type;
    private Boolean tv;
    private Boolean safe;
    private Boolean fridge;
    private Boolean ac;
    private Boolean internet;

    private LocationW location;

    public AccomodationW(Accomodation accomodation){
        this.name = accomodation.getName();
        this.category = accomodation.getCategory();
        this.type = accomodation.getType();
        this.tv = accomodation.getTv();
        this.safe = accomodation.getSafe();
        this.fridge = accomodation.getFridge();
        this.ac = accomodation.getAc();
        this.internet = accomodation.getInternet();
        this.location = new LocationW(accomodation.getLocation());
    }
}
