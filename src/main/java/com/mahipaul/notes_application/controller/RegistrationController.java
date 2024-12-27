package com.mahipaul.notes_application.controller;

import com.mahipaul.notes_application.dto.ErrorResponse;
import com.mahipaul.notes_application.dto.RegistrationRequest;
import com.mahipaul.notes_application.dto.SuccessResponse;
import com.mahipaul.notes_application.model.User;
import com.mahipaul.notes_application.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        // Check if username already exists
        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("failed"));
        }

        // Create a new user
        User user = new User();
        user.setName(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword()); // Encrypt password
        userRepository.save(user);

        // Return success message in JSON format
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse("User registered successfully!"));
    }
}
