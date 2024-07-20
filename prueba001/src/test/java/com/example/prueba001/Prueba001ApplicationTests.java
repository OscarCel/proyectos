package com.example.prueba001;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class Prueba001ApplicationTests {

	@Autowired
    private MockMvc mvc;

    @Test
    void testSaludar() throws Exception {
        mvc.perform(get("/prueba001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
