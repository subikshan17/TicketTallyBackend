package com.example.TicketTally.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TicketTally.repository.EventRepository;
import com.example.TicketTally.repository.GateLogRepository;
import com.example.TicketTally.repository.SystemUserRepository;
import com.example.TicketTally.repository.TicketRepository;

@Service
public class AnalyticsService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SystemUserRepository userRepository;

    @Autowired
    private GateLogRepository gateLogRepository;

    public Map<String, Object> getSystemStats() {

        Map<String, Object> stats = new HashMap<>();

        stats.put("totalEvents", eventRepository.count());
        stats.put("totalTickets", ticketRepository.count());
        stats.put("totalUsers", userRepository.count());
        stats.put("totalGateScans", gateLogRepository.count());

        return stats;
    }
}