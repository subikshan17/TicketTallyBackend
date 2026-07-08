package com.example.TicketTally.dto;

public class TicketDTO {

    private Long id;
    private String serialNumber;
    private String eventTitle;
    private String holderName;

    public TicketDTO() {
    }

    public TicketDTO(Long id, String serialNumber,
                     String eventTitle, String holderName) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.eventTitle = eventTitle;
        this.holderName = holderName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getEventTitle() { return eventTitle; }
    public void setEventTitle(String eventTitle) { this.eventTitle = eventTitle; }

    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
}