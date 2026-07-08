package com.example.TicketTally.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime generatedAt;

    @Pattern(regexp = "ANALYTICS|SECURITY", message = "Report type must be ANALYTICS or SECURITY")
    private String reportType;

    private String content;

    private String name;
    private String type;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Report() {
    }

    public Report(Long id, LocalDateTime generatedAt, String reportType, String content, String name, String type, Event event) {
        this.id = id;
        this.generatedAt = generatedAt;
        this.reportType = reportType;
        this.content = content;
        this.name = name;
        this.type = type;
        this.event = event;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }

    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
}