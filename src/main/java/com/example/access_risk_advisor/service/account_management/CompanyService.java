package com.example.access_risk_advisor.service.account_management;

import com.example.access_risk_advisor.exception.ResourceNotFoundException;
import com.example.access_risk_advisor.model.account_management.Company;
import com.example.access_risk_advisor.model.account_management.Department;
import com.example.access_risk_advisor.model.account_management.User;
import com.example.access_risk_advisor.repository.account_management.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final DepartmentService departmentService;

    public CompanyService(
            CompanyRepository companyRepository,
            UserService userService,
            DepartmentService departmentService) {
        this.companyRepository = companyRepository;
        this.userService = userService;
        this.departmentService = departmentService;
    }

    // Creates a new company with the provided details.
    public Company createCompany(Company company) {
        // Validate company fields
        if (company.getName() == null || company.getName().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be empty");
        }
        if (company.getDomain() == null || company.getDomain().isEmpty()) {
            throw new IllegalArgumentException("Company domain cannot be empty");
        }
        if (company.getAdminUserName() == null || company.getAdminUserName().isEmpty()) {
            throw new IllegalArgumentException("Admin user name cannot be empty");
        }
        if (company.getAdminUserEmail() == null || company.getAdminUserEmail().isEmpty()) {
            throw new IllegalArgumentException("Admin user email cannot be empty");
        }

        return doCreateCompany(
                company);
    }

    // Creates a new company with admin user and auto generated record id.
    private Company doCreateCompany(Company newCompany) {
        // 1. Ensure domain uniqueness
        if (companyRepository.existsByDomain(newCompany.getDomain())) {
            throw new IllegalArgumentException("A company with this domain already exists.");
        }

        // 2. Create and persist company (id will be auto-generated)
        int generatedCompanyId = companyRepository.saveAndReturnId(newCompany);
        newCompany.setIdCompany(generatedCompanyId);

        // 3. Create default "administration" department for this company
        Department adminDept = new Department("Administration", "ADMIN", generatedCompanyId);
        Department newAdminDept = departmentService.createDepartment(adminDept); // returns the saved department with generated ID
        newCompany.addDepartment(newAdminDept);

        // 4. Create admin user for this department and company
        User adminUser = new User(
                newCompany.getAdminUserName(),
                newCompany.getAdminUserEmail(),
                adminDept.getIdDepartment(),
                newCompany.getIdCompany()
        );

        User newAdminUser = userService.createUser(adminUser);
        newCompany.setAdminUser(newAdminUser);
        newCompany.setAdminUserEmail(newAdminUser.getEmail());
        newCompany.setAdminUserName(newAdminUser.getUsername());

        // 5. Save the company with the admin user and department
        // at this point newCompany has got its unique key ID and is ready to be saved to persistence
        return companyRepository.update(newCompany);
    }


    public Company getCompanyById(int id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + id + " not found"));
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company updateCompany(int id, Company updated) {
        Company existing = companyRepository.findById(id).orElseThrow();
        updated.setIdCompany(existing.getIdCompany()); // Ensure ID is preserved
        return companyRepository.save(updated);
    }

    public void deleteCompany(int id) {
        if (!companyRepository.deleteById(id)) {
            throw new ResourceNotFoundException("Company with ID " + id + " not found");
        }
    }
}
