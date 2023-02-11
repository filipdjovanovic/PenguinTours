package com.penguins.project.controller.Auth;

import com.penguins.project.model.User.UserEntity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserParam {
    private String username;
    private String password;

}
