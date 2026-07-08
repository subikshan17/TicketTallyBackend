package com.example.TicketTally.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TicketTally.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByTitle(String title);

}