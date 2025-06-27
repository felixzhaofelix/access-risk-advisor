package com.example.access_risk_advisor.service;

import com.example.access_risk_advisor.model.AccessRequest;
import com.example.access_risk_advisor.model.RiskAssessmentResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RiskAssessmentServiceTest {

    private RiskAssessmentService service;

    @BeforeEach
    void setUp() {
        service = new RiskAssessmentService();
    }

    @Test
    void testNormalAccessRequestReturnsApprove() {
        AccessRequest request = new AccessRequest();
        request.setUsername("bob");
        request.setRole("Finance");
        request.setRequestedSystem("Accounting App");
        request.setRequestedBy("admin");
        request.setTimeOfRequest("2025-06-26T11:00:00");

        RiskAssessmentResult result = service.assessRisk(request);

        assertEquals("approve", result.getDecision());
        assertTrue(result.getRiskScore() < 80);
    }

    @Test
    void testHRDevOpsRequestReturnsFlag() {
        AccessRequest request = new AccessRequest();
        request.setUsername("alice");
        request.setRole("HR");
        request.setRequestedSystem("DevOps Dashboard");
        request.setRequestedBy("alice");
        request.setTimeOfRequest("2025-06-26T10:00:00");

        RiskAssessmentResult result = service.assessRisk(request);

        assertEquals("flag", result.getDecision());
        assertTrue(result.getRiskScore() >= 80);
    }

    @Test
    void testRequestStorageInMemory() {
        int initialSize = service.getAllRequests().size();

        AccessRequest request = new AccessRequest();
        request.setUsername("tom");
        request.setRole("Engineering");
        request.setRequestedSystem("Internal Wiki");
        request.setRequestedBy("tom");
        request.setTimeOfRequest("2025-06-26T12:00:00");

        service.assessRisk(request);

        List<AccessRequest> all = service.getAllRequests();
        assertEquals(initialSize + 1, all.size());
        assertEquals("tom", all.get(all.size() - 1).getUsername());
    }

    @Test
    void testFinanceRequestReturnsApprove() {
        AccessRequest request = new AccessRequest();
        request.setUsername("bob");
        request.setRole("Finance");
        request.setRequestedSystem("Payroll");
        request.setRequestedBy("admin");
        request.setTimeOfRequest("2025-06-26T10:00:00");

        RiskAssessmentResult result = service.assessRisk(request);

        assertEquals("approve", result.getDecision());
        assertTrue(result.getRiskScore() < 80);
    }

}
