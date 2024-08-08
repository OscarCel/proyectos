package com.example.onetoone.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.onetoone.model.Cuenta;
import com.example.onetoone.model.Persona;
import com.example.onetoone.repository.PersonaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
@AutoConfigureMockMvc
class PersonaControllerIntegrationTest {

    @Autowired
    private PersonaRepository personaRepository;
    
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Persona persona;

    @BeforeEach
    public void setUp(){
        persona = new Persona();
        persona.setNombre("Nombre1");
        persona = personaRepository.save(persona);

        Cuenta cuenta = new Cuenta();
        cuenta.setNombre("cuenta");
        cuenta.setPersona(persona);

        persona.setCuenta(cuenta);
        personaRepository.save(persona);
        personaRepository.flush();
    }

    @Test
    void testCrear() throws Exception {
        Persona per = new Persona();
        per.setNombre("Nombre2");
        Cuenta cu = new Cuenta();
        cu.setNombre("cu");
        cu.setPersona(per);
        persona.setCuenta(cu);
        personaRepository.save(per);
        String mensaje = objectMapper.writeValueAsString(per);

        mockMvc.perform(post("/api/persona")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mensaje))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(per.getId()))
        .andReturn();
    }

    @Test
    void testListar() throws Exception {
        mockMvc.perform(get("/api/persona")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testLeer() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/persona/{1}", persona.getId())
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(persona.getId()))
            .andReturn();
    
        String body = mvcResult.getResponse().getContentAsString();
        Persona personaResultado = objectMapper.readValue(body, Persona.class);

        assertNotNull(personaResultado.getCuenta());
    }
    
    @Test
    void testActualizar() throws Exception {
        Persona per = new Persona();
        per.setId(1l);
        per.setNombre("Nombre3");
        Cuenta cu = new Cuenta();
        cu.setNombre("cu");
        cu.setPersona(per);
        persona.setCuenta(cu);
        personaRepository.save(per);
        String mensaje = objectMapper.writeValueAsString(per);

        mockMvc.perform(put("/api/persona")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mensaje))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    }

    @Test
    void testBorrar() throws Exception {
        mockMvc.perform(delete("/api/persona/{id}", persona.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}
