package com.example.tablas.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.tablas.Repository.ExpedienteRepository;
import com.example.tablas.models.Documento;
import com.example.tablas.models.Expediente;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
@AutoConfigureMockMvc
class ExpedienteControllerIntefrationTest {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Expediente expediente;

    @BeforeEach
    public void setUp(){
        expediente = new Expediente();
        expediente.setNombre("Nombre");
        expediente = expedienteRepository.save(expediente);

        Documento documento = new Documento();
        documento.setTitulo("Hola");
        documento.setExpediente(expediente);

        expediente.getDocumentos().add(documento);
        expedienteRepository.save(expediente);
        expedienteRepository.flush();
    }

    @AfterEach
    public void tearDown(){

    }

    @Test
    void testMostrar() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/api/expedientes/{1}", expediente.getId())
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(expediente.getId()))
            .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        Expediente expedienteResultado = objectMapper.readValue(body, Expediente.class);

        assertTrue(expedienteResultado.getDocumentos().size() >= 1);
    }

    @Test
    void testMostrar_no_encontrado() throws Exception{
        mockMvc.perform(get("/api/expedientes/{1}", expediente.getId()*5)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
    }
}
