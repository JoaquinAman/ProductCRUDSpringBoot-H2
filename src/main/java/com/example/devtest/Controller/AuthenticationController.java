package com.example.devtest.Controller;

import com.example.devtest.DTO.Request.AuthenticationRequest;
import com.example.devtest.DTO.Request.RegisterRequest;
import com.example.devtest.DTO.Response.AuthenticationResponse;
import com.example.devtest.Repository.IUserRepository;
import com.example.devtest.Service.Implementation.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(service.register(registerRequest));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest registerRequest
    ) {
        return ResponseEntity.ok(service.authenticate(registerRequest));
    }
}
