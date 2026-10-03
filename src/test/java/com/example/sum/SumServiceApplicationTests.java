package com.example.sum;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class SumServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void endToEndSum() throws Exception {
        String body = mockMvc.perform(MockMvcRequestBuilders.get("/api/sum").param("a", "40").param("b", "2"))
                .andReturn().getResponse().getContentAsString();
        assertEquals("{\"a\":40,\"b\":2,\"result\":42}", body);
    }
}
