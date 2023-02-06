package com.penguins.project.controller.Program;

import com.penguins.project.controller.Location.LocationParam;
import com.penguins.project.model.Location.Location;
import com.penguins.project.model.Program.Program;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ProgramParam {

    private String description;
    private String date;
    private Set<LocationParam> locations = new HashSet<>();

    public Program toProgram(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        Set<Location> locations = this.getLocations()
                .stream()
                .map(locationParam -> locationParam.toLocation())
                .collect(Collectors.toSet());

        Program program = Program
                .builder()
                .description(this.getDescription())
                .date(LocalDate.parse(this.getDate(),formatter))
                .locations(locations)
                .build();

        return program;
    }

}
