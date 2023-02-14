package com.penguins.project.model.Picture;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "picture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Picture {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO)
    private Long id;
    private String filePath;
}
