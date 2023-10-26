package com.assessmenlibrary.assessmenlibrary.auth;

import com.assessmenlibrary.assessmenlibrary.book.Book;
import com.assessmenlibrary.assessmenlibrary.config.JwtService;
import com.assessmenlibrary.assessmenlibrary.user.Role;
import com.assessmenlibrary.assessmenlibrary.user.User;
import com.assessmenlibrary.assessmenlibrary.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;

    //bring in the password encoder to encode password before saving to db
    private final PasswordEncoder passwordEncoder;

    //bring jwt service to generate token
    private final JwtService jwtService;

    //bring in authentication management
    private final AuthenticationManager authenticationManager;



    public ResponseEntity<?> register(RegisterRequest request) {
        // Step 1: Check the database for a user by email from the request
        Optional<User> userOptional = repository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(request.getEmail()+"is already taken");

        } else {
            //Harsh user password
            String hashedPassword = passwordEncoder.encode(request.getPassword());

            // Create a new User instance with the hashed password

            User newUser=	new User(1,
                    request.getPassword(),
                    request.getEmail(),
                    request.getLastname(),
                    request.getFirstname(),
                    request.getUsername(),
                    Role.USER
            );
            //replace password with hashed password
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));

            // Step 2: Add the user to the database
            User registeredUser = repository.save(newUser);

            //Step 4: generate Token
            var jwtToken = jwtService.generateToken(newUser);
            AuthenticationResponse response = new AuthenticationResponse(jwtToken, newUser);
            //send user information and token as response
            return ResponseEntity.ok(response);
        }
    }

    public ResponseEntity<?> authenticate(AuthenticationRequest request) {
        // Step 1: Check the database for a user by email from the request
        Optional<User> userOptional = repository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Step 2: If the email exists, check if the password in the request matches the user's password
            if (isPasswordMatch(request.getPassword(), user.getPassword())) {
                // Step 3: If the password matches, return user details
                var jwtToken = jwtService.generateToken(user);
                AuthenticationResponse response = new AuthenticationResponse(jwtToken, user);
                return ResponseEntity.ok(response);
            } else {
                // Step 4: If the password doesn't match
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("wrong password for " + user.getEmail());
            }
        } else {
            // Step 5: If the email doesn't exist, throw an exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found with" + request.getEmail());
        }
    }
    private boolean isPasswordMatch(String requestPassword, String userPassword) {
        return passwordEncoder.matches(requestPassword, userPassword);
    }
}