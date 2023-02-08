package com.penguins.project.controller.Program;

import com.penguins.project.service.ProgramService;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

}
