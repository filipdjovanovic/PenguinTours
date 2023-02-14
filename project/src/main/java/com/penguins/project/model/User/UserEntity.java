package com.penguins.project.model.User;


import com.penguins.project.model.Role.Role;
import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();
}

