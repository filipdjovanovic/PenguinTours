package com.penguins.project.controller.Program;


import com.penguins.project.controller.Location.LocationW;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
public class ProgramW {
    private String description;
    private String date;
    private Set<LocationW> locations = new HashSet<>();
}
