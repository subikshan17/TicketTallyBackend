package com.example.TicketTally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.TicketTally.dto.EventDTO;
import com.example.TicketTally.entity.Event;
import com.example.TicketTally.service.EventService;
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public Page<EventDTO> getAllEvents(@RequestParam(defaultValue = "0") int page) {
        return eventService.getAllEvents(PageRequest.of(page, 10));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }   

    @PostMapping
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER')")
    public ResponseEntity<EventDTO> createEvent(@RequestBody Event event) {

        EventDTO dto = new EventDTO(
                event.getId(),
                event.getTitle(),
                event.getVenue(),
                event.getStartTime(),
                event.getStatus().name()
        );

        return ResponseEntity.ok(eventService.createEvent(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER')")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventDTO dto) {
        return ResponseEntity.ok(eventService.updateEvent(id, dto));
    }

    @DeleteMapping("/{id}")
    
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER')")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}