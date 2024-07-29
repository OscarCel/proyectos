package com.example.tablas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.tablas.Repository.ExpedienteRepository;
import com.example.tablas.controllers.ExpedienteController;
import com.example.tablas.models.Expediente;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test") // Usar el perfil de test
public class ExpedienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ExpedienteRepository expedienteRepository;

    @InjectMocks
    private ExpedienteController expedienteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(expedienteController).build();
    }

    @Test
    public void testGetAllExpedientes() throws Exception {
        Expediente expediente1 = new Expediente();
        expediente1.setId(1L);
        expediente1.setNombre("Expediente 1");

        Expediente expediente2 = new Expediente();
        expediente2.setId(2L);
        expediente2.setNombre("Expediente 2");

        when(expedienteRepository.findAll()).thenReturn(Arrays.asList(expediente1, expediente2));

        this.mockMvc.perform(get("/expedientes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("[{\"id\":1,\"nombre\":\"Expediente 1\"},{\"id\":2,\"nombre\":\"Expediente 2\"}]"));
    }

    @Test
    public void testGetExpedienteById() throws Exception {
        Expediente expediente = new Expediente();
        expediente.setId(1L);
        expediente.setNombre("Expediente 1");

        when(expedienteRepository.findById(1L)).thenReturn(Optional.of(expediente));

        this.mockMvc.perform(get("/expedientes/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{\"id\":1,\"nombre\":\"Expediente 1\"}"));
    }

    @Test
    public void testCreateExpediente() throws Exception {
        Expediente expediente = new Expediente();
        expediente.setId(1L);
        expediente.setNombre("Nuevo Expediente");

        when(expedienteRepository.save(any(Expediente.class))).thenReturn(expediente);

        this.mockMvc.perform(post("/expedientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"nombre\":\"Nuevo Expediente\"}"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{\"id\":1,\"nombre\":\"Nuevo Expediente\"}"));
    }

    @Test
    public void testUpdateExpediente() throws Exception {
        Expediente expediente = new Expediente();
        expediente.setId(1L);
        expediente.setNombre("Expediente Actualizado");

        when(expedienteRepository.existsById(1L)).thenReturn(true);
        when(expedienteRepository.save(any(Expediente.class))).thenReturn(expediente);

        this.mockMvc.perform(put("/expedientes/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"nombre\":\"Expediente Actualizado\"}"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{\"id\":1,\"nombre\":\"Expediente Actualizado\"}"));
    }

    @Test
    public void testDeleteExpediente() throws Exception {
        when(expedienteRepository.existsById(1L)).thenReturn(true);

        this.mockMvc.perform(delete("/expedientes/1"))
            .andExpect(status().isNoContent());

        verify(expedienteRepository).deleteById(1L);
    }
}