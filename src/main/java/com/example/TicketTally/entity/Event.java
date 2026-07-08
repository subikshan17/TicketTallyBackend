package com.example.TicketTally.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;


@Entity
@Table(name = "events")
public class Event {

    public enum EventStatus {
        PUBLISHED,
        DRAFT,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
private String title;
    private String venue;
    private LocalDateTime startTime;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @JsonManagedReference
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<TicketTier> tiers;

    public Event() {
    }

    public Event(Long id, String title, String venue, LocalDateTime startTime, EventStatus status, List<TicketTier> tiers) {
        this.id = id;
        this.title = title;
        this.venue = venue;
        this.startTime = startTime;
        this.status = status;
        this.tiers = tiers;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public EventStatus getStatus() { return status; }
    public void setStatus(EventStatus status) { this.status = status; }

    public List<TicketTier> getTiers() { return tiers; }
    public void setTiers(List<TicketTier> tiers) { this.tiers = tiers; }
}