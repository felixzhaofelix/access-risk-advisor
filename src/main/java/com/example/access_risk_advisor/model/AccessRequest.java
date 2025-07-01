package com.example.access_risk_advisor.model;

import com.example.access_risk_advisor.model.account_management.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccessRequest {
    private int id;

    @NotNull(message = "User is required")
    @Valid
    private User user;

    @NotBlank(message = "Requested system is required")
    private String requestedSystem;

    @NotBlank(message = "RequestedBy is required")
    private String requestedBy;

    @NotBlank(message = "Time of request is required")
    private String timeOfRequest;

    public AccessRequest() {
    }

    public AccessRequest(User user, String requestedSystem, String requestedBy, String timeOfRequest) {
        this.user = user;
        this.requestedSystem = requestedSystem;
        this.requestedBy = requestedBy;
        this.timeOfRequest = timeOfRequest;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
