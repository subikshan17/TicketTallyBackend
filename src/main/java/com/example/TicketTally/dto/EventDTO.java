package com.example.TicketTally.dto;

import java.time.LocalDateTime;

public class EventDTO {

    private Long id;
    private String title;
    private String venue;
    private LocalDateTime startTime;
    private String status;

    public EventDTO() {
    }

    public EventDTO(Long id, String title, String venue,
                    LocalDateTime startTime, String status) {
        this.id = id;
        this.title = title;
        this.venue = venue;
        this.startTime = startTime;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}