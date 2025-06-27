package com.example.access_risk_advisor.service;

import com.example.access_risk_advisor.model.AccessRequest;
import com.example.access_risk_advisor.model.RiskAssessmentResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RiskAssessmentServiceTest {

    private RiskAssessmentService service;

    @BeforeEach
    void setUp() {
        service = new RiskAssessmentService();
    }

    @Test
    void testNormalAccessRequestReturnsApprove() {
        AccessRequest request = new AccessRequest("bob", "Finance", "Accounting App");
        request.setRequestedBy("admin");
        request.setTimeOfRequest("2025-06-26T11:00:00");

        RiskAssessmentResult result = service.assessRisk(request);

        assertEquals("approve", result.getDecision());
        assertTrue(result.getRiskScore() < 80);
    }

    @Test
    void testHRDevOpsRequestReturnsFlag() {
        AccessRequest request = new AccessRequest("alice", "HR", "DevOps Dashboard");
        request.setRequestedBy("alice");
        request.setTimeOfRequest("2025-06-26T10:00:00");

        RiskAssessmentResult result = service.assessRisk(request);

        assertEquals("flag", result.getDecision());
        assertTrue(result.getRiskScore() >= 80);
    }

    @Test
    void testRequestStorageInMemory() {
        int initialSize = service.getAllRequests().size();

        AccessRequest request = new AccessRequest("tom", "Engineering", "Internal Wiki");
        request.setRequestedBy("tom");
        request.setTimeOfRequest("2025-06-26T12:00:00");

        service.assessRisk(request);

        List<AccessRequest> all = service.getAllRequests();
        assertEquals(initialSize + 1, all.size());
        assertEquals("tom", all.get(all.size() - 1).getUsername());
    }

    @Test
    void testFinanceRequestReturnsApprove() {
        AccessRequest request = new AccessRequest("bob", "Finance", "Payroll");
        request.setRequestedBy("admin");
        request.setTimeOfRequest("2025-06-26T10:00:00");

        RiskAssessmentResult result = service.assessRisk(request);

        assertEquals("approve", result.getDecision());
        assertTrue(result.getRiskScore() < 80);
    }

    @Test
    public void testNormalAccessRequest_Approves() {
        AccessRequest request = new AccessRequest("alice", "IT", "Email");
        RiskAssessmentResult result = service.assessRisk(request);

        assertEquals("approve", result.getDecision());
        assertEquals(0, result.getRiskScore());
        assertEquals("Access appears normal.", result.getReason());
    }

    @Test
    public void testHighRiskRequest_HRtoDevOps_Flags() {
        AccessRequest request = new AccessRequest("carol", "HR", "DevOps Dashboard");
        RiskAssessmentResult result = service.assessRisk(request);

        assertEquals("flag", result.getDecision());
        assertEquals(85, result.getRiskScore());
        assertEquals("HR requesting DevOps system - unusual.", result.getReason());
    }

    @Test
    public void testRequestIsStoredWithId() {
        AccessRequest request = new AccessRequest("eve", "Sales", "CRM");
        service.assessRisk(request);

        List<AccessRequest> stored = service.getAllRequests();
        assertEquals(1, stored.size());
        assertEquals("eve", stored.get(0).getUsername());
        assertTrue(stored.get(0).getId() > 0);
    }

    @Test
    public void testRetrieveById_Success() {
        AccessRequest request = new AccessRequest("gina", "Finance", "Ledger");
        service.assessRisk(request);

        int id = request.getId();
        Optional<AccessRequest> result = service.getRequestById(id);

        assertTrue(result.isPresent());
        assertEquals("gina", result.get().getUsername());
    }

    @Test
    public void testDeleteById_RemovesRequest() {
        AccessRequest request = new AccessRequest("ivy", "Marketing", "Email");
        service.assessRisk(request);
        int id = request.getId();

        boolean deleted = service.deleteRequestById(id);
        assertTrue(deleted);

        Optional<AccessRequest> result = service.getRequestById(id);
        assertFalse(result.isPresent());
    }

}
