package com.example.access_risk_advisor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class AccessRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSubmitAccessRequest_ValidRequest_Returns200() throws Exception {
        String validJson = """
        {
            "username": "jane",
            "role": "Finance",
            "requestedSystem": "Payroll",
            "requestedBy": "admin",
            "timeOfRequest": "2025-06-26T14:30:00"
        }
    """;

        mockMvc.perform(post("/access-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.decision").value("approve"))
                .andExpect(jsonPath("$.riskScore").value(0))
                .andExpect(jsonPath("$.reason").value("Access appears normal."));
    }

    @Test
    void testSubmitAccessRequest_HRDevOps_ReturnsFlagged() throws Exception {
        String highRiskJson = """
        {
            "username": "alice",
            "role": "HR",
            "requestedSystem": "DevOps Monitoring",
            "requestedBy": "alice",
            "timeOfRequest": "2025-06-26T10:00:00"
        }
    """;

        mockMvc.perform(post("/access-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(highRiskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.decision").value("flag"))
                .andExpect(jsonPath("$.riskScore").value(85))
                .andExpect(jsonPath("$.reason").value("HR requesting DevOps system - unusual."));
    }

    @Test
    void testSubmitAccessRequest_MissingFields_Returns400() throws Exception {
        String invalidJson = "{}";

        mockMvc.perform(post("/access-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("username:")))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.path").exists());
    }


}