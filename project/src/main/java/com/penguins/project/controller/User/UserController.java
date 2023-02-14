package com.penguins.project.controller.User;


import com.penguins.project.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    @ResponseBody
    public List<UserW> getAllUsers(){
        return userService.findAllUsers();
    }
    @DeleteMapping(value = "/users/delete", params={"id"})
    public void deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
    }
}
