package com.penguins.project.service;

import com.penguins.project.model.Program.ProgramRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }
}
