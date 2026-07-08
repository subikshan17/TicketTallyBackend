package com.example.TicketTally.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TicketTally.dto.UserDTO;
import com.example.TicketTally.entity.SystemUser;
import com.example.TicketTally.exception.ResourceNotFoundException;
import com.example.TicketTally.repository.SystemUserRepository;

@Service
public class UserService {

    @Autowired
    private SystemUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getFullName(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }

    public Optional<SystemUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserDTO createUser(UserDTO userDto) {
        SystemUser user = new SystemUser();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setRole(userDto.getRole());

        user.setPassword(passwordEncoder.encode("password123"));

        SystemUser saved = userRepository.save(user);

        return new UserDTO(
                saved.getId(),
                saved.getEmail(),
                saved.getFullName(),
                saved.getRole()
        );
    }

    public SystemUser updateUser(Long id, SystemUser updatedUser) {
        SystemUser user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getEmail());
        user.setFullName(updatedUser.getFullName());
        user.setRole(updatedUser.getRole());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        SystemUser user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }
}