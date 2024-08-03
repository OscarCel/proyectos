package com.example.tablas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.tablas.models.Expediente;
import com.example.tablas.service.ExpedienteService;

@RestController
@RequestMapping("/api/expedientes")
public class ExpedienteController {

    @Value("${tablas.expediente.valor}")
    private long valor;

    @Autowired
    private ExpedienteService expedienteService;

    @GetMapping
    public List<Expediente> listar() {
        return expedienteService.listar();
    }

    @GetMapping("/{id}")
    public Expediente mostrar(@PathVariable("id") long id) {
        return expedienteService.mostrar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expediente crear(@RequestBody Expediente expediente) {
        expedienteService.crear(expediente);
        return expediente;
    }

    @PutMapping("/{id}")
    public Expediente actualizar(@PathVariable long id, @RequestBody Expediente expedienteDetalles) {
        Expediente expedienteExistente = expedienteService.mostrar(id);
        if (expedienteExistente == null) {
            throw new RuntimeException("Expediente no encontrado con ID: " + id);
        }
        expedienteExistente.setDocumentos(expedienteDetalles.getDocumentos());
        // Actualiza otros campos si es necesario
        expedienteService.actualizar(expedienteExistente);
        return expedienteExistente;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable long id) {
        Expediente expedienteExistente = expedienteService.mostrar(id);
        if (expedienteExistente == null) {
            throw new RuntimeException("Expediente no encontrado con ID: " + id);
        }
        expedienteService.borrar(expedienteExistente);
    }
}
