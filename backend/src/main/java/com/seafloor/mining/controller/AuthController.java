package com.seafloor.mining.controller;

import com.seafloor.mining.dto.LoginRequest;
import com.seafloor.mining.dto.RegisterRequest;
import com.seafloor.mining.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest request) {
        String operatorId = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Registration successful", "operatorId", operatorId));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {
        String operatorId = authService.login(request);
        return ResponseEntity.ok(Map.of("message", "Login successful", "operatorId", operatorId));
    }
}
