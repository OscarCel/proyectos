package com.example.tablas;

import com.example.tablas.models.Expediente;
import com.example.tablas.models.Documento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ExpedienteControllerIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    private Expediente expediente;
    private Documento documento;
    private long expedienteId;

    @BeforeEach
    public void setUp() throws Exception {
        documento = new Documento();
        documento.setTitulo("Documento de Prueba");

        expediente = new Expediente();
        expediente.setNombre("Expediente de Prueba");
        expediente.setDocumentos(List.of(documento));

        entityManager.persist(expediente);
        entityManager.flush();

        expedienteId = expediente.getId();
    }

    @AfterEach
    public void tearDown() {
        entityManager.createQuery("DELETE FROM Documento").executeUpdate();
        entityManager.createQuery("DELETE FROM Expediente").executeUpdate();
    }

    @Test
    void testGetAllExpedientes() throws Exception {
        mvc.perform(get("/expedientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Expediente de Prueba"))
                .andExpect(jsonPath("$[0].documentos[0].titulo").value("Documento de Prueba"));
    }

    @Test
    void testGetExpedienteById() throws Exception {
        mvc.perform(get("/expedientes/{id}", expedienteId)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expedienteId))
                .andExpect(jsonPath("$.nombre").value("Expediente de Prueba"))
                .andExpect(jsonPath("$.documentos[0].titulo").value("Documento de Prueba"))
                .andReturn();
    }

    @Test
    void testCreateExpediente() throws Exception {
        Expediente nuevoExpediente = new Expediente();
        nuevoExpediente.setNombre("Nuevo Expediente");
        nuevoExpediente.setDocumentos(List.of(new Documento(null, "Documento Nuevo", null)));

        String mensaje = objectMapper.writeValueAsString(nuevoExpediente);

        MvcResult mvcResult = mvc.perform(post("/expedientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mensaje))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        Expediente expedienteCreado = objectMapper.readValue(responseBody, Expediente.class);

        // Validar que el expediente fue creado correctamente
        mvc.perform(get("/expedientes/{id}", expedienteCreado.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nuevo Expediente"))
                .andExpect(jsonPath("$.documentos[0].titulo").value("Documento Nuevo"));
    }

    @Test
    @Disabled
    void testUpdateExpediente() throws Exception {
        Expediente expedienteActualizado = new Expediente();
        expedienteActualizado.setNombre("Expediente Actualizado");
        expedienteActualizado.setDocumentos(List.of(new Documento(null, "Documento Actualizado", null)));

        String mensaje = objectMapper.writeValueAsString(expedienteActualizado);

        mvc.perform(put("/expedientes/{id}", expedienteId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mensaje))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Expediente Actualizado"))
                .andExpect(jsonPath("$.documentos[0].titulo").value("Documento Actualizado"));
    }


    @Test
    void testDeleteExpediente() throws Exception {
        // Crear un expediente que luego se eliminará
        Expediente nuevoExpediente = new Expediente();
        nuevoExpediente.setNombre("Expediente para eliminar");
        nuevoExpediente.setDocumentos(List.of(new Documento(null, "Documento de prueba para eliminar", null)));

        String expedienteJson = objectMapper.writeValueAsString(nuevoExpediente);

        MvcResult mvcResult = mvc.perform(post("/expedientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expedienteJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        Expediente expedienteCreado = objectMapper.readValue(responseBody, Expediente.class);
        Long expedienteIdParaEliminar = expedienteCreado.getId();

        // Eliminar el expediente con el ID recién creado
        mvc.perform(delete("/expedientes/{id}", expedienteIdParaEliminar)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

}
