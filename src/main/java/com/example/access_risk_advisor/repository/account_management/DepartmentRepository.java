package com.example.access_risk_advisor.repository.account_management;

import com.example.access_risk_advisor.model.account_management.Company;
import com.example.access_risk_advisor.model.account_management.Department;

import java.util.*;

//@Repository
public class DepartmentRepository {
    private final Map<Integer, Department> store = new HashMap<>();
    private int nextId = 1;

    public Department save(Department department) {
        department.setIdDepartment(nextId++);
        store.put(department.getIdDepartment(), department);
        return department;
    }

    public int saveAndReturnId(Department department) {
        return save(department).getIdDepartment();
    }

    public Optional<Department> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Department> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean deleteById(int id) {
        return store.remove(id) != null;
    }

}
