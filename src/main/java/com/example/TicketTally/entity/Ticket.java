package com.example.TicketTally.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @NotBlank(message = "Serial number cannot be blank")
    @Column(unique = true)
     private String serialNumber;

    private LocalDateTime purchaseTime;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;

    @ManyToOne
    @JoinColumn(name = "tier_id")
    private TicketTier tier;

    @ManyToOne
    @JoinColumn(name = "holder_id")
    private SystemUser holder;

    public Ticket() {
    }

    public Ticket(Long id, String serialNumber, LocalDateTime purchaseTime, Event event, SystemUser user, TicketTier tier, SystemUser holder) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.purchaseTime = purchaseTime;
        this.event = event;
        this.user = user;
        this.tier = tier;
        this.holder = holder;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public LocalDateTime getPurchaseTime() { return purchaseTime; }
    public void setPurchaseTime(LocalDateTime purchaseTime) { this.purchaseTime = purchaseTime; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public SystemUser getUser() { return user; }
    public void setUser(SystemUser user) { this.user = user; }

    public TicketTier getTier() { return tier; }
    public void setTier(TicketTier tier) { this.tier = tier; }

    public SystemUser getHolder() { return holder; }
    public void setHolder(SystemUser holder) { this.holder = holder; }
}
