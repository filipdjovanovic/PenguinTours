package com.penguins.project.controller.Accomodation;

import com.penguins.project.model.Accomodation.Accomodation;
import lombok.Getter;

@Getter
public class AccomodationParam {
    private String name;
    private Integer category;
    private String type;
    private Boolean tv;
    private Boolean safe;
    private Boolean fridge;
    private Boolean ac;
    private Boolean internet;
    private String picture1;
    private String picture2;
    private String picture3;
    private String picture4;
    private String picture5;
    private String picture6;

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
                .picture1(this.picture1)
                .picture2(this.picture2)
                .picture3(this.picture3)
                .picture4(this.picture4)
                .picture5(this.picture5)
                .picture6(this.picture6)
                .build();
        return accomodation;
    }
}
