package com.example.TicketTally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.TicketTally.dto.AuthRequestDto;
import com.example.TicketTally.dto.RegisterRequestDto;
import com.example.TicketTally.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickettally")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto dto) {
        authService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }
}