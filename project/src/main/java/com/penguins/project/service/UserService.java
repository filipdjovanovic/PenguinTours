package com.penguins.project.service;



import com.penguins.project.controller.User.UserW;
import com.penguins.project.model.User.UserEntity;
import com.penguins.project.model.User.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public void deleteUser(Long id){
        boolean exists = userRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("User with id " + id + " does not exists");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public List<UserW> findAllUsers(){
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserW> userWList = userEntityList.stream()
                .map(user -> new UserW(user))
                .collect(Collectors.toList());

        return userWList;
    }
}
