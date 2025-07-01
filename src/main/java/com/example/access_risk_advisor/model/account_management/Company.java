package com.example.access_risk_advisor.model.account_management;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private int idCompany;

    @NotBlank(message = "Company name is required")
    private String name;

    @NotBlank(message = "Industry is required")
    private String industry;

    @NotBlank(message = "Domain is required")
    private String domain;

    private String location;

    @NotNull(message = "Admin User Email is required on company creation")
    private String adminUserEmail;

    @NotBlank(message = "Admin User Name is required on company creation")
    private String adminUserName;

    private List<Department> departments = new ArrayList<>();

    private List<SystemResource> systemResources = new ArrayList<>();

    private User adminUser;

    public Company() {
    }

    public Company(String name, String industry, String domain, String location, String adminUserEmail, User adminUser) {
        this.name = name;
        this.industry = industry;
        this.domain = domain;
        this.location = location;
        this.adminUserEmail = adminUserEmail;
        this.adminUser = adminUser;

    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAdminUserEmail() {
        return adminUserEmail;
    }

    public void setAdminUserEmail(String adminUserEmail) {
        this.adminUserEmail = adminUserEmail;
    }

    public User getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(User adminUser) {
        this.adminUser = adminUser;
        addUserToDepartment(adminUser.getDepartmentId(), adminUser);
    }

    // Company doesn't manage users directly, but rather through departments
    public void addUserToDepartment(int departmentId, User user) {
        for (Department department : departments) {
            if (department.getIdDepartment() == departmentId) {
                department.addUser(user);
                return;
            }
        }
        throw new IllegalArgumentException("Department with ID " + departmentId + " not found in company");
    }

    public void removeUserFromDepartment(String username) {
        for (Department department : departments) {
            if (department.hasUser(username)) {
                department.removeUser(department.getUsers().stream()
                        .filter(user -> user.getUsername().equals(username))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("User '" + username + "' not found in department")));
                return;
            }
        }
        throw new IllegalArgumentException("User '" + username + "' not found in any department of this company");
    }


    public List<Department> getDepartments() {
        return departments;
    }

    public void addDepartment(Department department) {
        this.departments.add(department);
    }

    public void addSystemResource(SystemResource resource) {
        this.systemResources.add(resource);
    }

    public List<SystemResource> getSystemResources() {
        return systemResources;
    }

    public void removeSystemResource(SystemResource resource) {
        this.systemResources.remove(resource);
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminUserName() {
        return this.adminUserName;
    }
}
