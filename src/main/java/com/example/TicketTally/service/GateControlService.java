package com.example.TicketTally.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.TicketTally.entity.GateLog;
import com.example.TicketTally.entity.Ticket;
import com.example.TicketTally.repository.GateLogRepository;
import com.example.TicketTally.repository.TicketRepository;

@Service
public class GateControlService {

    private final TicketRepository ticketRepository;
    private final GateLogRepository gateLogRepository;

    public GateControlService(TicketRepository ticketRepository, GateLogRepository gateLogRepository) {
        this.ticketRepository = ticketRepository;
        this.gateLogRepository = gateLogRepository;
    }

    public GateLog.ScanResult processEntry(String serialNumber, Long controllerId) {

        if (serialNumber == null || serialNumber.isBlank()) {
            return GateLog.ScanResult.INVALID;
        }

        Optional<Ticket> optionalTicket = ticketRepository.findBySerialNumber(serialNumber);

        if (optionalTicket.isEmpty()) {
            return GateLog.ScanResult.INVALID;
        }

        Ticket ticket = optionalTicket.get();

        boolean alreadyUsed = gateLogRepository.existsByTicket_IdAndStatus(ticket.getId(), "VALID");

        if (alreadyUsed) {
            return GateLog.ScanResult.ALREADY_USED;
        }

        GateLog gateLog = new GateLog();
        gateLog.setTicket(ticket);
        gateLog.setGateId(String.valueOf(controllerId));
        gateLog.setStatus("VALID");
        gateLog.setScanTime(LocalDateTime.now());

        gateLogRepository.save(gateLog);

        return GateLog.ScanResult.VALID;
    }
}