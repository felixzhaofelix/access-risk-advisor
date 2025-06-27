package com.example.access_risk_advisor.model;

public class RiskAssessmentResult {
    private String decision;  // approve / flag / deny
    private int riskScore;    // 0-100
    private String reason;    // explanation

    public RiskAssessmentResult(String decision, int riskScore, String reason) {
        this.decision = decision;
        this.riskScore = riskScore;
        this.reason = reason;
    }

    // Getters and setters
    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
