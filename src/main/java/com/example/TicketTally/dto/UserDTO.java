package com.example.TicketTally.dto;

import com.example.TicketTally.entity.SystemUser.UserRole;

public class UserDTO {

    private Long id;
    private String email;
    private String fullName;
    private UserRole role;

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String fullName, UserRole role) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
}
