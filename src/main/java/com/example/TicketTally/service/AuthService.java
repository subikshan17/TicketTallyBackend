package com.example.TicketTally.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TicketTally.dto.AuthRequestDto;
import com.example.TicketTally.dto.AuthResponseDto;
import com.example.TicketTally.dto.RegisterRequestDto;
import com.example.TicketTally.entity.SystemUser;
import com.example.TicketTally.exception.BusinessValidationException;
import com.example.TicketTally.exception.ResourceNotFoundException;
import com.example.TicketTally.repository.SystemUserRepository;
import com.example.TicketTally.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private SystemUserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponseDto login(AuthRequestDto dto) {

        SystemUser user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BusinessValidationException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDto(
                token,
                user.getEmail(),
                user.getFullName(),
                user.getRole()
        );
    }

    public void register(RegisterRequestDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessValidationException("User already exists");
        }

        SystemUser user = new SystemUser();
        user.setUsername(dto.getEmail());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setRole(dto.getRole());

        userRepository.save(user);
    }
}