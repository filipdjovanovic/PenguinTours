package com.penguins.project.controller.Program;


import com.penguins.project.controller.Location.LocationW;
import com.penguins.project.model.Program.Program;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
public class ProgramW {
    private String description;
    private String date;
    private Set<LocationW> locations = new HashSet<>();

    public ProgramW (Program program){
        this.description = program.getDescription();
        this.date = program.getDate().toString();
        this.locations = program.getLocations()
                .stream()
                .map(Location -> new LocationW(Location))
                .collect(Collectors.toSet());
    }
}
