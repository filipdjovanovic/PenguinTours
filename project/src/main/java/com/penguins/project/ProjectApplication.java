package com.penguins.project;

import com.penguins.project.model.Role.Role;
import com.penguins.project.model.Role.RoleRepository;
import com.penguins.project.model.User.UserEntity;
import com.penguins.project.model.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository,RoleRepository roleRepository) {
		return args -> {
			Role role = new Role();
			role.setName("ADMIN");
			roleRepository.save(role);
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			UserEntity user = new UserEntity();
			user.setUsername("admin");
			//user.setPassword(passwordEncoder.encode("admin"));
			user.setPassword("admin");
			Role roles = roleRepository.findByName("ADMIN").get();
			user.setRoles(Collections.singletonList(roles));
			userRepository.save(user);



		};
	}
	 */

}
