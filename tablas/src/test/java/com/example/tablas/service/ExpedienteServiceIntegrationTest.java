package com.example.tablas.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tablas.Repository.ExpedienteRepository;
import com.example.tablas.models.Documento;
import com.example.tablas.models.Expediente;

@SpringBootTest
public class ExpedienteServiceIntegrationTest {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @Autowired
    private ExpedienteService expedienteService;

    private Expediente savedExpediente;

    @BeforeEach
    public void setUp() {
        // Limpiamos el repositorio antes de cada prueba
        expedienteRepository.deleteAll();

        // Creamos y guardamos un expediente con un documento
        Expediente expediente = new Expediente();
        Documento documento = new Documento();
        documento.setTitulo("Hola");
        documento.setExpediente(expediente);
        expediente.getDocumentos().add(documento);

        savedExpediente = expedienteRepository.saveAndFlush(expediente);
    }

    @AfterEach
    public void tearDown() {
        // Limpiamos el repositorio después de cada prueba
        expedienteRepository.deleteAll();
    }

    @Test
    void testListar() {
        List<Expediente> res = expedienteService.listar();
        assertTrue(res.size() >= 1, "No existe al menos un registro en la lista.");
    }

    @Test
    void testMostrar() {
        Expediente res = expedienteService.mostrar(savedExpediente.getId());
        assertNotNull(res, "El expediente no debería ser nulo.");
    }

    @Test
    void testCrear() {
        Expediente nuevoExpediente = new Expediente();
        expedienteService.crear(nuevoExpediente);
        
        List<Expediente> res = expedienteService.listar();
        assertTrue(res.size() > 1, "Debería haber al menos dos expedientes después de la creación.");
    }

    @Test
    void testActualizar() {
        savedExpediente.setNombre("Nombre Actualizado");
        expedienteService.actualizar(savedExpediente);
        
        Expediente actualizado = expedienteService.mostrar(savedExpediente.getId());
        assertEquals("Nombre Actualizado", actualizado.getNombre(), "El documento no fue actualizado correctamente.");
    }

    @Test
    void testBorrar() {
        expedienteService.borrar(savedExpediente);
        
        Optional<Expediente> res = expedienteRepository.findById(savedExpediente.getId());
        assertFalse(res.isPresent(), "El expediente debería haber sido eliminado.");
    }
}
