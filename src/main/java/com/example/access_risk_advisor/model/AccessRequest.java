package com.example.access_risk_advisor.model;

import jakarta.validation.constraints.NotBlank;

public class AccessRequest {
    private int id;
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "Requested system is required")
    private String requestedSystem;

    @NotBlank(message = "RequestedBy is required")
    private String requestedBy;

    @NotBlank(message = "Time of request is required")
    private String timeOfRequest;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getRequestedSystem() {
        return requestedSystem;
    }
    public void setRequestedSystem(String requestedSystem) {
        this.requestedSystem = requestedSystem;
    }

    public String getRequestedBy() {
        return requestedBy;
    }
    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getTimeOfRequest() {
        return timeOfRequest;
    }
    public void setTimeOfRequest(String timeOfRequest) {
        this.timeOfRequest = timeOfRequest;
    }
}
