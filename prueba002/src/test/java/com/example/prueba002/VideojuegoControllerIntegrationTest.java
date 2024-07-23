package com.example.prueba002;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.MvcResult;

import com.example.prueba002.model.Videojuego;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class VideojuegoControllerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void testList() throws Exception {
        mvc.perform(get("/api/videojuego")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGet() throws Exception {
        int id = 1;
        MvcResult mvcResult = mvc.perform(get("/api/videojuego/get/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        Videojuego videojuegoResultado = objectMapper.readValue(body, Videojuego.class);
        
        assertFalse(videojuegoResultado.isLanzado());
    }

    @Test
    void testDelete() throws Exception {
        mvc.perform(delete("/api/videojuego/delete/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testCrear() throws Exception {
        //String mensaje = "{\"id\":1,\"name\":\"juego\",\"lanzado\":false,\"}";
        Videojuego videojuego = new Videojuego(1l, "juego", false);
        String mensaje = objectMapper.writeValueAsString(videojuego);

        MvcResult mvcResult = mvc.perform(post("/api/videojuego")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mensaje))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("1"))
        .andReturn();

    }

    @Test
    @Disabled
    void testActualizar() throws Exception{
        fail("no implementado aun");
    }

}
