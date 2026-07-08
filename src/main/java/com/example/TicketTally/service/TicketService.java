package com.example.TicketTally.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TicketTally.dto.PurchaseRequestDto;
import com.example.TicketTally.dto.TicketDTO;
import com.example.TicketTally.entity.Event;
import com.example.TicketTally.entity.SystemUser;
import com.example.TicketTally.entity.Ticket;
import com.example.TicketTally.entity.TicketTier;
import com.example.TicketTally.exception.BusinessValidationException;
import com.example.TicketTally.exception.ResourceNotFoundException;
import com.example.TicketTally.repository.SystemUserRepository;
import com.example.TicketTally.repository.TicketRepository;
import com.example.TicketTally.repository.TicketTierRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketTierRepository ticketTierRepository;

    @Autowired
    private SystemUserRepository userRepository;

    public List<TicketDTO> getTicketsByUser(Long userId) {
        return ticketRepository.findByUser_Id(userId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TicketDTO purchaseTicket(PurchaseRequestDto dto, Long userId) {

        SystemUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        TicketTier tier = ticketTierRepository.findById(dto.getTierId())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket tier not found"));

        if (tier.getRemainingQuantity() == null || tier.getRemainingQuantity() <= 0) {
            throw new BusinessValidationException("Ticket tier is sold out");
        }

        Event event = tier.getEvent();

        if (event == null) {
            throw new BusinessValidationException("Ticket tier is not associated with any event");
        }

        tier.setRemainingQuantity(tier.getRemainingQuantity() - 1);
        ticketTierRepository.save(tier);

        Ticket ticket = new Ticket();
        ticket.setSerialNumber("TT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        ticket.setPurchaseTime(LocalDateTime.now());
        ticket.setUser(user);
        ticket.setHolder(user);
        ticket.setTier(tier);
        ticket.setEvent(event);

        Ticket saved = ticketRepository.save(ticket);

        return mapToDto(saved);
    }

    public TicketDTO updateTicket(Long id, PurchaseRequestDto dto) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        TicketTier tier = ticketTierRepository.findById(dto.getTierId())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket tier not found"));

        ticket.setTier(tier);
        ticket.setEvent(tier.getEvent());

        Ticket updated = ticketRepository.save(ticket);

        return mapToDto(updated);
    }

    public void cancelTicket(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        TicketTier tier = ticket.getTier();

        if (tier != null) {
            tier.setRemainingQuantity(tier.getRemainingQuantity() + 1);
            ticketTierRepository.save(tier);
        }

        ticketRepository.delete(ticket);
    }

    private TicketDTO mapToDto(Ticket ticket) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getSerialNumber(),
                ticket.getEvent() != null ? ticket.getEvent().getTitle() : null,
                ticket.getHolder() != null ? ticket.getHolder().getFullName() : null
        );
    }
}