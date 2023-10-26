package com.assessmenlibrary.assessmenlibrary.auth;

import com.assessmenlibrary.assessmenlibrary.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    //endpoint that needs no auth Token
    @PostMapping("register")
    public ResponseEntity<ResponseEntity<Optional<User>>> register(
            @RequestBody RegisterRequest request
    ){
        //register information
        return ResponseEntity.ok((ResponseEntity<Optional<User>>) service.register(request));
    }

    @PostMapping("authenticate")
    public ResponseEntity<ResponseEntity<Optional<User>>> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        //register information
        return ResponseEntity.ok((ResponseEntity<Optional<User>>) service.authenticate(request));
    }

    @GetMapping("home")
    public String home (){
        return ("Hello this is working and needs no authentication");
    }
}
