package com.penguins.project.controller.User;


import com.penguins.project.model.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserW {

    private Long id;
    private String username;

    public UserW(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
    }
}
