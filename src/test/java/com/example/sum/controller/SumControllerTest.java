package com.example.sum.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.sum.exception.GlobalExceptionHandler;
import com.example.sum.service.SumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SumController.class)
@Import(GlobalExceptionHandler.class)
class SumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SumService sumService;

    @Test
    void returnsSum() throws Exception {
        when(sumService.add(2, 3)).thenReturn(5L);

        mockMvc.perform(get("/api/sum").param("a", "2").param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a").value(2))
                .andExpect(jsonPath("$.b").value(3))
                .andExpect(jsonPath("$.result").value(5));
    }

    @Test
    void missingParameterReturns400() throws Exception {
        mockMvc.perform(get("/api/sum").param("a", "2"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Required parameter 'b' is missing"));
    }

    @Test
    void nonIntegerParameterReturns400() throws Exception {
        mockMvc.perform(get("/api/sum").param("a", "abc").param("b", "3"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Parameter 'a' must be a valid 32-bit integer"));
    }

    @Test
    void outOfRangeIntegerReturns400() throws Exception {
        mockMvc.perform(get("/api/sum").param("a", "2147483648").param("b", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void unexpectedErrorReturns500WithoutLeakingDetails() throws Exception {
        when(sumService.add(anyInt(), anyInt())).thenThrow(new IllegalStateException("secret internals"));

        mockMvc.perform(get("/api/sum").param("a", "1").param("b", "1"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("An unexpected error occurred"));
    }
}
