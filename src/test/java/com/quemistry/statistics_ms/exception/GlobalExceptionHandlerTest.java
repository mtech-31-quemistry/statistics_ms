package com.quemistry.statistics_ms.exception;

import com.quemistry.statistics_ms.controller.StatisticsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticsController controller;

    @Test
    void whenRuntimeExceptionThrown_thenInternalServerErrorResponse() throws Exception {
        // Given
        doThrow(new RuntimeException("Test RuntimeException"))
                .when(controller)
                .getMcqStatistics(any(), any()); // This will trigger the exception

        // When & Then
        MvcResult result = mockMvc.perform(get("/v1/stats/mcq")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(jsonPath("$.message").value("Internal Server Error"))
                .andReturn();

        // Assert
        String responseBody = result.getResponse().getContentAsString();
        assertTrue(responseBody.contains("Internal Server Error"));
    }
}
