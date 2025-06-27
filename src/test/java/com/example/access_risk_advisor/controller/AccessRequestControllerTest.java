package com.example.access_risk_advisor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.access_risk_advisor.service.RiskAssessmentService;

@SpringBootTest
@AutoConfigureMockMvc
class AccessRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

//    @Test
//    void testGetRequestById_NotFound_Returns404() throws Exception {
//        int nonexistentId = 999;
//
//        when(riskService.getRequestById(nonexistentId)).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/access-request/{id}", nonexistentId))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.message").value("Request not found"))
//                .andExpect(jsonPath("$.timestamp").exists())
//                .andExpect(jsonPath("$.path").exists());
//    }
}