package com.example.TicketTally.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TicketTally.entity.SystemUser;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

    Optional<SystemUser> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<SystemUser> findByUsername(String username);

    boolean existsByUsername(String username);
}