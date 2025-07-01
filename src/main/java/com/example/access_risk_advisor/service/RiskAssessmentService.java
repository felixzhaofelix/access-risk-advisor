package com.example.access_risk_advisor.service;

import com.example.access_risk_advisor.model.AccessRequest;
import com.example.access_risk_advisor.model.RiskAssessmentResult;
import com.example.access_risk_advisor.model.account_management.User;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RiskAssessmentService {
    private static final Logger logger = LoggerFactory.getLogger(RiskAssessmentService.class);
    private final List<AccessRequest> requests = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public RiskAssessmentResult assessRisk(AccessRequest request) {
        int riskScore = 0;
        String reason = "Access appears normal.";

        User user = request.getUser();
        String role = user.getRole().toLowerCase();
        String system = request.getRequestedSystem().toLowerCase();

        if (role.equals("hr") && system.contains("devops")) {
            riskScore = 85;
            reason = "HR requesting DevOps system - unusual.";
        }

        String decision = riskScore >= 80 ? "flag" : "approve";

        // Assign ID and store the request
        request.setId(idCounter.getAndIncrement());
        requests.add(request);

        logger.info("Access request assessed: user={}, system={}, decision={}, riskScore={}",
                user.getUsername(), request.getRequestedSystem(), decision, riskScore);

        return new RiskAssessmentResult(decision, riskScore, reason);
    }

    public List<AccessRequest> getAllRequests() {
        logger.info("Returning all stored access requests (total = {})", requests.size());
        return requests;
    }

    public Optional<AccessRequest> getRequestById(int id) {
        logger.info("Searching for access request with ID {}", id);
        return requests.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
    }

    public boolean deleteRequestById(int id) {
        logger.info("Deleting access request with ID {}", id);
        return requests.removeIf(r -> r.getId() == id);
    }
}
