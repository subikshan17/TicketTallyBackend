package com.example.TicketTally.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "gate_logs")
public class GateLog {

    public enum ScanResult {
        VALID,
        INVALID,
        ALREADY_USED,
        ACCESS_DENIED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime scanTime;

    @NotBlank(message = "Gate ID cannot be blank")
    private String gateId;

    private String status;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public GateLog() {
    }

    public GateLog(Long id, LocalDateTime scanTime, String gateId, String status, Ticket ticket) {
        this.id = id;
        this.scanTime = scanTime;
        this.gateId = gateId;
        this.status = status;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getScanTime() {
        return scanTime;
    }

    public void setScanTime(LocalDateTime scanTime) {
        this.scanTime = scanTime;
    }

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}