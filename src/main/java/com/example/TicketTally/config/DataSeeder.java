package com.example.TicketTally.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.TicketTally.entity.SystemUser;
import com.example.TicketTally.entity.SystemUser.UserRole;
import com.example.TicketTally.repository.SystemUserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private SystemUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (!userRepository.existsByUsername("admin")) {

            SystemUser admin = new SystemUser();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("System Admin");
            admin.setRole(UserRole.SYSTEM_ADMIN);

            userRepository.save(admin);
        }
    }
}