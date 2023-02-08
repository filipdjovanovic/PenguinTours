package com.penguins.project.controller.Accomodation;

import com.penguins.project.controller.Location.LocationParam;
import com.penguins.project.model.Accomodation.Accomodation;
import lombok.Getter;

@Getter
public class AccomodationParam {
    private String name;
    private String category;
    private String type;
    private Boolean tv;
    private Boolean safe;
    private Boolean fridge;
    private Boolean ac;
    private Boolean internet;
    private LocationParam location;

    public Accomodation toAccomodation(){
        Accomodation accomodation = Accomodation.builder()
                .name(this.name)
                .category(this.category)
                .type(this.type)
                .tv(this.tv)
                .safe(this.safe)
                .fridge(this.fridge)
                .ac(this.ac)
                .internet(this.internet)
                .location(this.location.toLocation())
                .build();
        return accomodation;
    }
}
