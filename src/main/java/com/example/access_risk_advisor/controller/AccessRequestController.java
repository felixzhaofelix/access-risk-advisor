package com.example.access_risk_advisor.controller;

import com.example.access_risk_advisor.model.AccessRequest;
import com.example.access_risk_advisor.model.RiskAssessmentResult;
import com.example.access_risk_advisor.service.RiskAssessmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Tag(name = "Access Requests", description = "Endpoints for submitting and managing access requests")
@RestController
@RequestMapping("/access-request")
public class AccessRequestController {
    private static final Logger logger = LoggerFactory.getLogger(AccessRequestController.class);

    @Autowired
    private RiskAssessmentService riskService;

    @GetMapping
    public List<AccessRequest> getAllRequests() {
        logger.info("Fetching all access requests");
        return riskService.getAllRequests();
    }

    @GetMapping("/{id}")
    public AccessRequest getRequestById(@PathVariable int id) {
        logger.info("Fetching access request with ID {}", id);
        return riskService.getRequestById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
    }

    @PostMapping
    public RiskAssessmentResult submitAccessRequest(@Valid @RequestBody AccessRequest request) {
        logger.info("Received new access request from {} for {}", request.getUsername(), request.getRequestedSystem());
        return riskService.assessRisk(request);
    }

    @DeleteMapping("/{id}")
    public String deleteRequest(@PathVariable int id) {
        logger.info("Attempting to delete request with ID {}", id);
        boolean removed = riskService.deleteRequestById(id);
        if (removed) {
            logger.info("Successfully deleted request {}", id);
        } else {
            logger.warn("Failed to delete request {} — not found", id);
        }
        return removed ? "Deleted request " + id : "Request not found";
    }

}
