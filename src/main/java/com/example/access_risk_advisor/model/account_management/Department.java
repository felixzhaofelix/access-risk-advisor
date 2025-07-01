package com.example.access_risk_advisor.model.account_management;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private int idDepartment;

    @NotBlank(message = "Department name is required")
    private String name;

    @NotBlank(message = "Department code is required")
    private String code;

    @NotNull(message = "Company ID is required")
    private Integer companyId;

    private String description;

    private List<User> users = new ArrayList<>();

    public Department() {
    }

    public Department(String name, String code, Integer companyId) {
        this.name = name;
        this.code = code;
        this.companyId = companyId;
    }

    // Full constructor
    public Department(String name, String code, Integer companyId, String description) {
        this.name = name;
        this.code = code;
        this.companyId = companyId;
        this.description = description;
    }

    // Getters and setters
    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if (user != null && !users.contains(user)) {
            users.add(user);
            user.setDepartmentId(this.idDepartment); // Ensure user knows its department
        }
    }

    public void removeUser(User user) {
        if (user != null) {
            users.remove(user);
            user.setDepartmentId(0); // Clear department ID if removed
        }
    }

    public boolean hasUser(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }
}
