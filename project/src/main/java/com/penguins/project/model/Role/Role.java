package com.penguins.project.model.Role;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}