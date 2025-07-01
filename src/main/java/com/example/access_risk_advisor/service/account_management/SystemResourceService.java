package com.example.access_risk_advisor.service.account_management;

import com.example.access_risk_advisor.exception.ResourceNotFoundException;
import com.example.access_risk_advisor.model.account_management.SystemResource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SystemResourceService {

    private final Map<Integer, List<SystemResource>> companyResources = new HashMap<>();
    private final AtomicInteger resourceIdCounter = new AtomicInteger(1);

    public SystemResource addSystemResource(int companyId, SystemResource resource) {
        int id = resourceIdCounter.getAndIncrement();
        resource.setId(id);
        resource.setCompanyId(companyId);

        companyResources.computeIfAbsent(companyId, k -> new ArrayList<>()).add(resource);
        return resource;
    }

    public List<SystemResource> getAllSystemResources(int companyId) {
        return companyResources.getOrDefault(companyId, Collections.emptyList());
    }

    public SystemResource getSystemResourceById(int companyId, int resourceId) {
        return companyResources.getOrDefault(companyId, Collections.emptyList())
                .stream()
                .filter(r -> r.getId() == resourceId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public boolean deleteSystemResource(int companyId, int resourceId) {
        List<SystemResource> resources = companyResources.get(companyId);
        if (resources == null) return false;
        return resources.removeIf(r -> r.getId() == resourceId);
    }

    public SystemResource updateSystemResource(int companyId, int resourceId, SystemResource updatedResource) {
        List<SystemResource> resources = companyResources.get(companyId);
        if (resources == null) {
            throw new ResourceNotFoundException("Company not found or has no resources");
        }

        for (int i = 0; i < resources.size(); i++) {
            SystemResource existing = resources.get(i);
            if (existing.getId() == resourceId) {
                updatedResource.setId(resourceId);
                updatedResource.setCompanyId(companyId);
                resources.set(i, updatedResource);
                return updatedResource;
            }
        }

        throw new ResourceNotFoundException("Resource with ID " + resourceId + " not found for company " + companyId);
    }
}
