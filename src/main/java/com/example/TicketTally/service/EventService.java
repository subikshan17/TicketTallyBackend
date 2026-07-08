package com.example.TicketTally.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.TicketTally.exception.ResourceNotFoundException;
import com.example.TicketTally.dto.EventDTO;
import com.example.TicketTally.entity.Event;
import com.example.TicketTally.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Page<EventDTO> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable).map(event ->
                new EventDTO(event.getId(), event.getTitle(), event.getVenue(),
                        event.getStartTime(), event.getStatus().toString())
        );
    }

    public Optional<EventDTO> getEventById(Long id) {
        return eventRepository.findById(id).map(event ->
                new EventDTO(event.getId(), event.getTitle(), event.getVenue(),
                        event.getStartTime(), event.getStatus().toString())
        );
    }

    public EventDTO createEvent(EventDTO eventDto) {
        Event event = new Event();
        event.setTitle(eventDto.getTitle());
        event.setVenue(eventDto.getVenue());
        event.setStartTime(eventDto.getStartTime());
        event.setStatus(Event.EventStatus.valueOf(eventDto.getStatus()));

        Event saved = eventRepository.save(event);

        return new EventDTO(saved.getId(), saved.getTitle(), saved.getVenue(),
                saved.getStartTime(), saved.getStatus().toString());
    }

    public EventDTO updateEvent(Long id, EventDTO updatedDto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        event.setTitle(updatedDto.getTitle());
        event.setVenue(updatedDto.getVenue());
        event.setStartTime(updatedDto.getStartTime());
        event.setStatus(Event.EventStatus.valueOf(updatedDto.getStatus()));

        Event saved = eventRepository.save(event);

        return new EventDTO(saved.getId(), saved.getTitle(), saved.getVenue(),
                saved.getStartTime(), saved.getStatus().toString());
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}