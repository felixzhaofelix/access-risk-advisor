package com.example.access_risk_advisor.controller.account_management;

import com.example.access_risk_advisor.model.account_management.Company;
import com.example.access_risk_advisor.service.account_management.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Company Management", description = "Endpoints for managing companies")
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public Company registerCompany(@Valid @RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable int id, @Valid @RequestBody Company updated) {
        return companyService.updateCompany(id, updated);
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
        return "Company deleted";
    }
}
