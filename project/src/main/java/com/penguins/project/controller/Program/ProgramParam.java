package com.penguins.project.controller.Program;

import com.penguins.project.controller.Lokacija.LokacijaParam;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ProgramParam {

    private String opis;
    private String datum;
    private Set<LokacijaParam> lokacije = new HashSet<>();
}
