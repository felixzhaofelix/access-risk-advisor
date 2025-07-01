package com.example.access_risk_advisor.model.account_management;

import jakarta.validation.constraints.NotBlank;

public class SystemResource {
    private int id;

    @NotBlank(message = "System name is required")
    private String name;

    @NotBlank(message = "System type is required")
    private String type;

    @NotBlank(message = "Access level is required")
    private String accessLevel;

    private String location;

    private int companyId;

    public SystemResource() {
    }

    public SystemResource(String name, String type, String accessLevel, String location, int companyId) {
        this.name = name;
        this.type = type;
        this.accessLevel = accessLevel;
        this.location = location;
        this.companyId = companyId;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
