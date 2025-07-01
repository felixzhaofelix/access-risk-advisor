package com.example.access_risk_advisor.controller.account_management;

import com.example.access_risk_advisor.model.account_management.SystemResource;
import com.example.access_risk_advisor.service.account_management.SystemResourceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "System Resource Management", description = "Endpoints for managing system resources per company")
@RestController
@RequestMapping("/company/{companyId}/system-resource")
public class SystemResourceController {

    private static final Logger logger = LoggerFactory.getLogger(SystemResourceController.class);
    private final SystemResourceService systemResourceService;

    public SystemResourceController(SystemResourceService systemResourceService) {
        this.systemResourceService = systemResourceService;
    }

    @PostMapping
    public SystemResource addSystemResource(@PathVariable int companyId, @Valid @RequestBody SystemResource resource) {
        logger.info("Adding system resource '{}' for company {}", resource.getName(), companyId);
        return systemResourceService.addSystemResource(companyId, resource);
    }

    @GetMapping
    public List<SystemResource> getAllSystemResources(@PathVariable int companyId) {
        logger.info("Fetching all system resources for company {}", companyId);
        return systemResourceService.getAllSystemResources(companyId);
    }

    @GetMapping("/{resourceId}")
    public SystemResource getSystemResourceById(@PathVariable int companyId, @PathVariable int resourceId) {
        logger.info("Fetching system resource {} for company {}", resourceId, companyId);
        return systemResourceService.getSystemResourceById(companyId, resourceId);
    }

    @DeleteMapping("/{resourceId}")
    public String deleteSystemResource(@PathVariable int companyId, @PathVariable int resourceId) {
        logger.info("Deleting system resource {} from company {}", resourceId, companyId);
        boolean removed = systemResourceService.deleteSystemResource(companyId, resourceId);
        return removed ? "Deleted system resource " + resourceId : "Resource not found";
    }

    @PutMapping("/{resourceId}")
    public SystemResource updateSystemResource(
            @PathVariable int companyId,
            @PathVariable int resourceId,
            @Valid @RequestBody SystemResource updatedResource) {

        logger.info("Updating system resource {} for company {}", resourceId, companyId);
        return systemResourceService.updateSystemResource(companyId, resourceId, updatedResource);
    }

}
