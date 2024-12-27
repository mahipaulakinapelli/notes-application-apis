package com.mahipaul.notes_application.controller;

import com.mahipaul.notes_application.dto.LoginBody;

import com.mahipaul.notes_application.model.User;
import com.mahipaul.notes_application.repository.UserRepository;
import com.mahipaul.notes_application.config.security.JwtUtil;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")  // Allow requests from this origin
@ToString
@Slf4j
public class LoginController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginBody loginRequest) throws Throwable {

        log.info("hello hii {} ",loginRequest.getPassword());
        log.info("hello email {} ",loginRequest.getEmail());
        Optional<User> userOptional = userRepository.findByName(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
           // if (passwordEncoder.matches(passwordEncoder.encode(loginRequest.getPassword()), user.getPassword())) {
            if(loginRequest.getPassword().equals(user.getPassword())) {
                // Generate a real JWT token using JwtUtil
                String token = jwtUtil.generateToken(user.getName());
                return  new ResponseEntity<>("{ \"token\": \"" + token + "\" }",HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("Invalid creds",HttpStatus.BAD_REQUEST);
    }
}
