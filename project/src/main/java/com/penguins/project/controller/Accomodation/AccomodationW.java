package com.penguins.project.controller.Accomodation;


import com.penguins.project.controller.Location.LocationW;
import com.penguins.project.model.Accomodation.Accomodation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Builder
@Getter
@AllArgsConstructor
public class AccomodationW {
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
    //private LocationW location;

    public AccomodationW(Accomodation accomodation) throws IOException {
        this.name = accomodation.getName();
        this.category = accomodation.getCategory();
        this.type = accomodation.getType();
        this.tv = accomodation.getTv();
        this.safe = accomodation.getSafe();
        this.fridge = accomodation.getFridge();
        this.ac = accomodation.getAc();
        this.internet = accomodation.getInternet();
        if (accomodation.getPicture1()!=null){
            this.picture1 = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(accomodation.getPicture1())));
        }else{
            this.picture1 = null;
        }
        if (accomodation.getPicture2()!=null){
            this.picture2 = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(accomodation.getPicture2())));
        }else{
            this.picture2 = null;
        }
        if (accomodation.getPicture3()!=null){
            this.picture3 = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(accomodation.getPicture3())));
        }else{
            this.picture3 = null;
        }
        if (accomodation.getPicture4()!=null){
            this.picture4 = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(accomodation.getPicture4())));
        }else{
            this.picture4 = null;
        }
        if (accomodation.getPicture5()!=null){
            this.picture5 = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(accomodation.getPicture5())));
        }else{
            this.picture5 = null;
        }
        if (accomodation.getPicture6()!=null){
            this.picture6 = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(accomodation.getPicture6())));
        }else{
            this.picture6 = null;
        }

        //this.location = new LocationW(accomodation.getLocation());
    }
}
