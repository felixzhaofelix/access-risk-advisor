package com.example.access_risk_advisor.repository.account_management;

import com.example.access_risk_advisor.model.account_management.Company;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CompanyRepository {
    private final Map<Integer, Company> store = new HashMap<>();
    private int nextId = 1;

    public Company save(Company company) {
        company.setIdCompany(nextId++);
        store.put(company.getIdCompany(), company);
        return company;
    }

    public int saveAndReturnId(Company company) {
        return save(company).getIdCompany();
    }

    //method to update an existing company by id
    public Company update(Company updatedCompany) {
        if (!store.containsKey(updatedCompany.getIdCompany())) {
            throw new NoSuchElementException("Company with ID " + updatedCompany.getIdCompany() + " does not exist.");
        }
        store.put(updatedCompany.getIdCompany(), updatedCompany);
        return updatedCompany;
    }

    public Optional<Company> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Company> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean deleteById(int id) {
        return store.remove(id) != null;
    }

    public boolean existsByDomain(String domain) {
        return store.values().stream().anyMatch(c -> c.getDomain().equalsIgnoreCase(domain));
    }
}
