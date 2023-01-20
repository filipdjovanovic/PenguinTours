package com.penguins.project.controller.Aranzman;

import com.penguins.project.controller.Program.ProgramParam;
import com.penguins.project.controller.Smestaj.SmestajParam;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class AranzmanParam {
    private String naziv;
    private Integer cena;
    private String prevoz;
    private String status;

    //private Integer broj_dana;
    private String napomena;
    private Set<ProgramParam> programi = new HashSet<>();
    private Set<SmestajParam> smestaji = new HashSet<>();
}
