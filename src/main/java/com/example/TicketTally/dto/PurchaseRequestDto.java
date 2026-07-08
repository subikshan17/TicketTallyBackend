package com.example.TicketTally.dto;

import jakarta.validation.constraints.NotNull;

public class PurchaseRequestDto {

    @NotNull(message = "Ticket Tier ID is required")
    private Long tierId;

    public PurchaseRequestDto() {
    }

    public PurchaseRequestDto(Long tierId) {
        this.tierId = tierId;
    }

    public Long getTierId() {
        return tierId;
    }

    public void setTierId(Long tierId) {
        this.tierId = tierId;
    }
}