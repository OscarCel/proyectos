package com.example.tablas.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Persistent;
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
public class ExpedienteServiceIntegrationTest {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExpedienteService expedienteService;

    @Autowired
    ObjectMapper objectMapper;

    private Expediente expediente;

    @BeforeEach
    public void setUp() {

        expediente = new Expediente();
        expediente = expedienteRepository.save(expediente);

        Documento documento = new Documento();
        documento.setTitulo("Hola");
        documento.setExpediente(expediente);

        expediente.getDocumentos().add(documento);
        expedienteRepository.save(expediente);
        expedienteRepository.flush();
    }

    @AfterEach
    public void tearDown() {
        expedienteRepository.deleteAll();
    }

    @Test
    void testListar() {
        List<Expediente> res = expedienteService.listar();
        assertTrue(res.size() >= 1, "No existe al menos un registro en la lista.");
    }

    @Test
    public void testMostrar() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/expedientes/{id}", expediente.getId())
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
    void testCrear() {
        Expediente nuevoExpediente = new Expediente();
        expedienteService.crear(nuevoExpediente);
        
    }

    @Test
    void testActualizarCambiaNombreBorraDocumento() {
        Expediente expedienteLeido = new Expediente();
        expedienteLeido.setId(expediente.getId());
        expedienteLeido.setNombre("Nombre Actualizado");

        expedienteService.actualizar(expedienteLeido);
    }

    @Test
    void testActualizarActualizarDocumento() {
        Expediente expedienteLeido = new Expediente();
        expedienteLeido.setId(expediente.getId());
        expedienteLeido.setNombre("Nombre Actualizado");
        
        expedienteService.actualizar(expedienteLeido);
    }

    @Test
    @Disabled
    void testBorrar() {
        expedienteService.borrar(expediente);
        
    }
}
