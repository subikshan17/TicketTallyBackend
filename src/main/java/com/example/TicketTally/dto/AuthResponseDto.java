package com.example.TicketTally.dto;

import com.example.TicketTally.entity.SystemUser.UserRole;

public class AuthResponseDto {

    private String token;
    private String email;
    private String fullName;
    private UserRole role;

    public AuthResponseDto() {
    }

    public AuthResponseDto(String token, String email, String fullName, UserRole role) {
        this.token = token;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}