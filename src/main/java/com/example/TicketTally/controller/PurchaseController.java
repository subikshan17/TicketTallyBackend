package com.example.TicketTally.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.TicketTally.dto.PurchaseRequestDto;
import com.example.TicketTally.dto.TicketDTO;
import com.example.TicketTally.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('TICKET_HOLDER')")
    public ResponseEntity<List<TicketDTO>> getTicketsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(ticketService.getTicketsByUser(userId));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PostMapping
    public ResponseEntity<TicketDTO> purchaseTicket(@Valid @RequestBody PurchaseRequestDto dto,
                                                    @RequestParam Long userId) {
        return ResponseEntity.ok(ticketService.purchaseTicket(dto, userId));
    }

    @PostMapping("/manual")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER')")
    public ResponseEntity<TicketDTO> manualBooking(@Valid @RequestBody PurchaseRequestDto dto,
                                                   @RequestParam Long userId) {
        return ResponseEntity.ok(ticketService.purchaseTicket(dto, userId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER')")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id,
                                                  @Valid @RequestBody PurchaseRequestDto dto) {
        return ResponseEntity.ok(ticketService.updateTicket(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER','TICKET_HOLDER')")
    public ResponseEntity<Void> cancelTicket(@PathVariable Long id) {
        ticketService.cancelTicket(id);
        return ResponseEntity.noContent().build();
    }
}