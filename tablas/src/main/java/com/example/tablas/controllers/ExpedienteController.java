package com.example.tablas.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tablas.Repository.ExpedienteRepository;
import com.example.tablas.models.Documento;
import com.example.tablas.models.Expediente;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expedientes")
public class ExpedienteController {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @GetMapping
    public List<Expediente> getAllExpedientes() {
        return expedienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Expediente getExpedienteById(@PathVariable Long id) {
        Optional<Expediente> expediente = expedienteRepository.findById(id);
        return expediente.orElse(null);
    }

    @PostMapping
    public ResponseEntity<Expediente> createExpediente(@RequestBody Expediente expediente) {
        Expediente nuevoExpediente = expedienteRepository.save(expediente);
        return ResponseEntity.ok(nuevoExpediente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expediente> updateExpediente(@PathVariable Long id, @RequestBody Expediente expedienteActualizado) {
        if (!expedienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        // Obtener el expediente existente
        Expediente expedienteExistente = expedienteRepository.findById(id).orElseThrow();
        // Actualizar el expediente existente
        expedienteExistente.setNombre(expedienteActualizado.getNombre());
        // Actualizar los documentos de manera segura
        List<Documento> documentosActualizados = expedienteActualizado.getDocumentos();
        expedienteExistente.getDocumentos().clear(); // Primero limpia la colección
        if (documentosActualizados != null) {
            expedienteExistente.getDocumentos().addAll(documentosActualizados); // Luego agrega los nuevos documentos
        }
        // Guardar el expediente actualizado
        Expediente expedienteGuardado = expedienteRepository.save(expedienteExistente);
        return ResponseEntity.ok(expedienteGuardado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpediente(@PathVariable Long id) {
        if (!expedienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        expedienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
