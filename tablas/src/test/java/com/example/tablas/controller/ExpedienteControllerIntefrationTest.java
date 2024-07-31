package com.example.tablas.controller;

import com.example.tablas.models.Expediente;
import com.example.tablas.service.ExpedienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExpedienteControllerIntefrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExpedienteService expedienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Expediente expediente;

    @BeforeEach
    public void setUp() {
        expediente = new Expediente();
        expedienteService.crear(expediente);
    }

    @Test
    public void testListar() throws Exception {
        mockMvc.perform(get("/api/expedientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").isNotEmpty());
    }

    @Test
    public void testMostrar() throws Exception {
        mockMvc.perform(get("/api/expedientes/{id}", expediente.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(expediente.getId()));
    }

    @Test
    public void testCrear() throws Exception {
        Expediente nuevoExpediente = new Expediente();
        mockMvc.perform(post("/api/expedientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoExpediente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void testActualizar() throws Exception {
        expediente.setDocumentos(null); // o establecer datos relevantes
        mockMvc.perform(put("/api/expedientes/{id}", expediente.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expediente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expediente.getId()));
    }

    @Test
    public void testBorrar() throws Exception {
        mockMvc.perform(delete("/api/expedientes/{id}", expediente.getId()))
                .andExpect(status().isNoContent());
    }
}
