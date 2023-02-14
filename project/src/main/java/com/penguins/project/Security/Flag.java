package com.penguins.project.Security;


import lombok.Data;

@Data
public class Flag {
    private String name;

    public Flag(String flag) {
        this.name = flag;
    }
}

