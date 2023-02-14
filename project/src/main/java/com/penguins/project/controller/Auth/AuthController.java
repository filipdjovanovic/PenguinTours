package com.penguins.project.controller.Auth;


import com.penguins.project.Security.JWTGenerator;
import com.penguins.project.Security.JwtTokenUtil;
import com.penguins.project.model.Role.Role;
import com.penguins.project.model.Role.RoleRepository;
import com.penguins.project.model.User.UserEntity;
import com.penguins.project.model.User.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTGenerator jwtGenerator;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refreshToken(@RequestHeader(value = "Authorization") String authorization) {
        String token = authorization.substring(7);
        Claims claims = JwtTokenUtil.getAllClaimsFromToken(token);
        String subject = claims.getSubject();
        String role = "STAFF";
        if (subject.equals("admin")){
            role = "ADMIN";
        }
        if (JwtTokenUtil.isTokenExpired(token)) {
            return ResponseEntity.badRequest().body(null);
        }
        String refreshedToken = JwtTokenUtil.generateToken(claims.getSubject());
        return ResponseEntity.ok(new AuthResponseDTO(refreshedToken,role));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserParam userParam){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userParam.getUsername(),
                            userParam.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            String role = authorities.stream().collect(Collectors.toList()).get(0).toString();
            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<>(new AuthResponseDTO(token,role), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new AuthResponseDTO("ERROR","ERROR"), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserParam userParam){
        if (userRepository.existsByUsername(userParam.getUsername())){
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(userParam.getUsername());
        user.setPassword(passwordEncoder.encode(userParam.getPassword()));
        Role roles = roleRepository.findByName("STAFF").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);

    }
}
