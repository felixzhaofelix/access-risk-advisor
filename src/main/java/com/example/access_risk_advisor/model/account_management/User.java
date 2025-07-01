package com.example.access_risk_advisor.model.account_management;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class User {

    private int idUser;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "A valid email is required")
    @NotBlank(message = "Email is required")
    private String email;

    private String role;

    private int departmentId;

    private int companyId;

    private String location;

    private String employeeCode;

    private boolean isAdmin = false;

    // Constructors
    public User() {
    }

    public User(String username, String email, String role, int departmentId, int companyId, String location, String employeeCode, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.departmentId = departmentId;
        this.companyId = companyId;
        this.location = location;
        this.employeeCode = employeeCode;
        this.isAdmin = isAdmin;
    }

    // Constructor for admin user
    public User(String username, String email, int departmentId, int companyId) {
        this.username = username;
        this.email = email;
        this.role = "superuser";
        this.departmentId = departmentId;
        this.companyId = companyId;
        this.employeeCode = "1"; // Default employee ID for admin
        this.isAdmin = true;

    }

    // Getters & Setters

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
