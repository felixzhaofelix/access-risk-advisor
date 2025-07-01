package com.example.access_risk_advisor.controller.account_management;

import com.example.access_risk_advisor.exception.ResourceNotFoundException;
import com.example.access_risk_advisor.model.account_management.Department;
import com.example.access_risk_advisor.service.account_management.CompanyService;
import com.example.access_risk_advisor.service.account_management.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Department Management", description = "Endpoints for managing departments")
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    private final DepartmentService departmentService;
    private final CompanyService companyService;

    public DepartmentController(DepartmentService departmentService, CompanyService companyService) {
        this.departmentService = departmentService;
        this.companyService = companyService;
    }

    @PostMapping
    public Department createDepartment(@Valid @RequestBody Department department) {
        logger.info("Creating department: {}", department.getName());

        // Validate company existence
        if (companyService.getCompanyById(department.getCompanyId()) == null) {
            throw new ResourceNotFoundException("Company with ID " + department.getCompanyId() + " not found");
        }

        return departmentService.createDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        logger.info("Fetching all departments");
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable int id) {
        logger.info("Fetching department with ID {}", id);
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/companies/{companyId}/departments")
    public List<Department> getDepartmentsByCompany(@PathVariable int companyId) {
        logger.info("Fetching departments for company ID {}", companyId);

        if (companyService.getCompanyById(companyId) == null) {
            throw new ResourceNotFoundException("Company with ID " + companyId + " not found");
        }

        return departmentService.getDepartmentsByCompany(companyId);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable int id, @Valid @RequestBody Department updatedDepartment) {
        logger.info("Updating department with ID {}", id);
        return departmentService.updateDepartment(id, updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable int id) {
        logger.info("Deleting department with ID {}", id);
        departmentService.deleteDepartment(id);
        return "Deleted department with ID " + id;
    }
}
