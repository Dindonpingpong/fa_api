package com.example.pizza.controller;

import com.example.pizza.exception.ResourceNotFoundException;
import com.example.pizza.model.Client;
import com.example.pizza.payload.*;
import com.example.pizza.repository.UserRepository;
import com.example.pizza.security.CurrentUser;
import com.example.pizza.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
                currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(),
                currentUser.getAddress(), currentUser.getPhone());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        Client client = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));


        UserProfile userProfile = new UserProfile(client.getId(), client.getUsername(), client.getFirstName(), client.getCreatedAt());

        return userProfile;
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUserProfile(@PathVariable long id, @RequestBody SignUpRequest signUpRequest) {
        try {
            Client client = userRepository.getOne(id);

            client.setUsername(signUpRequest.getUsername());
            client.setFirstName(signUpRequest.getFirstName());
            client.setLastName(signUpRequest.getLastName());
            client.setAddress(signUpRequest.getAddress());
            client.setEmail(signUpRequest.getEmail());
            client.setPhone(signUpRequest.getPhone());
            if (signUpRequest.getPassword() != null) {
                client.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            }
            userRepository.save(client);
            return new ResponseEntity<>(new ApiResponse(true,"User updated"),OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "User not updated"),CONFLICT);
        }
    }
}
