package com.penguins.project.controller.Arrangement;

import com.penguins.project.controller.Program.ProgramParam;
import com.penguins.project.controller.Accomodation.AccomodationParam;
import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Program.Program;
import com.penguins.project.model.Accomodation.Accomodation;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@ToString
public class ArrangementParam {
    private String name;
    private Integer price;
    private String transportation;
    private String status;
    private String remark;
    private Set<ProgramParam> programs = new HashSet<>();
    private Set<AccomodationParam> accomodations = new HashSet<>();

    public Arrangement toArrangement(){
        Set<Accomodation> accomodations = this.getAccomodations()
                .stream()
                .map(accomodationParam -> accomodationParam.toAccomodation())
                .collect(Collectors.toSet());

        Set<Program> programs = this.getPrograms()
                .stream()
                .map(programParam -> programParam.toProgram())
                .collect(Collectors.toSet());

        Arrangement arrangement = Arrangement.builder()
                .name(this.getName())
                .price(this.getPrice())
                .transportation(this.getTransportation())
                .status(this.getStatus())
                .remark(this.getRemark())
                .accomodations(accomodations)
                .programs(programs)
                .build();

        return arrangement;

    }
}
