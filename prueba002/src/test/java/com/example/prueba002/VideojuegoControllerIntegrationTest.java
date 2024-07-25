package com.example.prueba002;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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

    //test  que comprueba que list en controller funciona
    @Test
    void testList() throws Exception {
        mvc.perform(get("/api/videojuego")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    //test  que comprueba que get en controller funciona
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

    //test  que comprueba que delete en controller funciona
    @Test
    void testDelete() throws Exception {
        mvc.perform(delete("/api/videojuego/delete/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //test  que comprueba que crear en controller funciona
    @Test
    void testCrear() throws Exception {
        //String mensaje = "{\"id\":3,\"name\":\"juego\",\"lanzado\":false,\"}";
        Videojuego videojuego = new Videojuego(3l, "juego", false);
        String mensaje = objectMapper.writeValueAsString(videojuego);

        mvc.perform(post("/api/videojuego/crear")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mensaje))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("3"))
        .andReturn();
    }

    //comprobar que en caso de que el nombre no culmpla las condiciones envie un 400
    @Test
    void testCrearNombreValidate() throws Exception {
        Videojuego videojuego = new Videojuego(3l, "game", false);
        String mensaje = objectMapper.writeValueAsString(videojuego);

        mvc.perform(post("/api/videojuego/crear")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mensaje))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("3"))
        .andReturn();
    }

    //test  que comprueba que actualizar en controller funciona
    @Test
    void testActualizar() throws Exception{
        Videojuego videojuego = new Videojuego(3l, "juego", false);
        String mensaje = objectMapper.writeValueAsString(videojuego);

        mvc.perform(put("/api/videojuego")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mensaje))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    }

}
