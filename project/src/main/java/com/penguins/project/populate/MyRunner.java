package com.penguins.project.populate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    public static boolean flag = false;

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    private final Populator populator;

    public MyRunner(Populator populator) {
        this.populator = populator;
    }


    @Override
    public void run(String... args) throws Exception {
        flag = false;
        populator.populateRole();
        populator.populateUser();
        populator.populateArrangements();
        flag = true;

    }
}
