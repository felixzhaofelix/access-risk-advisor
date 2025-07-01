package com.example.access_risk_advisor.service.account_management;

import com.example.access_risk_advisor.exception.ResourceNotFoundException;
import com.example.access_risk_advisor.model.account_management.Company;
import com.example.access_risk_advisor.model.account_management.Department;
import com.example.access_risk_advisor.repository.account_management.CompanyRepository;
import com.example.access_risk_advisor.repository.account_management.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository = new DepartmentRepository();

    public DepartmentService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Department createDepartment(Department department) {
        // Validate that company exists
        companyRepository.findById(department.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + department.getCompanyId() + " not found."));

        return doCreateDepartment(department);
    }

    private Department doCreateDepartment(Department newDepartment) {
        return departmentRepository.save(newDepartment); // returns the saved department with generated ID
    }

    public List<Department> getAllDepartments() {
        return new ArrayList<>(departmentRepository.findAll());
    }

    public Department updateDepartment(int id, Department updated) {
        Department existing = departmentRepository.findById(updated.getIdDepartment()).orElseThrow();
        updated.setIdDepartment(existing.getIdDepartment()); // Ensure ID is preserved
        return departmentRepository.save(updated);
    }

    public void deleteDepartment(int id) {
        if (!departmentRepository.deleteById(id)) {
            throw new ResourceNotFoundException("Department with ID " + id + " not found.");
        }
    }

    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with ID " + id + " not found."));
    }

    public List<Department> getDepartmentsByCompany(int companyId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + companyId + " not found."));

        return departmentRepository.findAll().stream()
                .filter(department -> department.getCompanyId() == companyId)
                .collect(Collectors.toList());
    }
}
