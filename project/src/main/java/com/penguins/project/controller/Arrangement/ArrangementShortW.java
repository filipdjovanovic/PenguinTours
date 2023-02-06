package com.penguins.project.controller.Arrangement;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArrangementShortW {

    private Long id;
    private String name;
    private Integer price;
    private String transportation;
    private String city;
    private String country;
    private String continent;
    private Long numberOfDays;
    private Date startDate;
    private Date endDate;

    public ArrangementShortW(Object[] obj){
        this.setId((Long) obj[0]);
        this.setName((String) obj[1]);
        this.setPrice((Integer) obj[2]);
        this.setTransportation((String) obj[3]);
        this.setCity((String) obj[4]);
        this.setCountry((String) obj[5]);
        this.setContinent((String) obj[6]);
        this.setNumberOfDays((Long) obj[7]);
        this.setStartDate((Date) obj[8]);
        this.setEndDate((Date) obj[9]);
    }

}
