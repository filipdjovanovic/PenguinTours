package com.penguins.project.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select (count(u) > 0) from UserEntity u where u.username = ?1")
    boolean existsByUsername(String username);
    @Query("select u from UserEntity u where u.username = ?1")
    UserEntity findByUsername(String username);




}
