package com.example.TicketTally.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.TicketTally.entity.TicketTier;
import com.example.TicketTally.repository.TicketTierRepository;

@RestController
@RequestMapping("/api/tickettiers")
public class TicketTierController {

    @Autowired
    private TicketTierRepository ticketTierRepository;

    @PostMapping
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER')")
    public ResponseEntity<TicketTier> createTier(@RequestBody TicketTier tier) {
        return ResponseEntity.ok(ticketTierRepository.save(tier));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<TicketTier>> getTiersByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(ticketTierRepository.findByEvent_Id(eventId));
    }
    @PutMapping("/{id}")
@PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER')")
public ResponseEntity<TicketTier> updateTier(@PathVariable Long id,
                                             @RequestBody TicketTier updatedTier) {

    TicketTier tier = ticketTierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket tier not found"));

    tier.setTierName(updatedTier.getTierName());
    tier.setPrice(updatedTier.getPrice());
    tier.setCapacity(updatedTier.getCapacity());
    tier.setRemainingQuantity(updatedTier.getRemainingQuantity());
    tier.setEvent(updatedTier.getEvent());

    return ResponseEntity.ok(ticketTierRepository.save(tier));
}

@DeleteMapping("/{id}")
@PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER')")
public ResponseEntity<Void> deleteTier(@PathVariable Long id) {
    ticketTierRepository.deleteById(id);
    return ResponseEntity.noContent().build();
}
}