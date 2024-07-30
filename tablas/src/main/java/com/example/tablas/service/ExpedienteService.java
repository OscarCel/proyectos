package com.example.tablas.service;

import com.example.tablas.models.Expediente;

import java.util.List;
import java.util.Optional;

public interface ExpedienteService {
    List<Expediente> obtenerTodosLosExpedientes();
    Optional<Expediente> obtenerExpedientePorId(Long id);
    Expediente crearExpediente(Expediente expediente);
    Optional<Expediente> actualizarExpediente(Long id, Expediente expediente);
    boolean eliminarExpediente(Long id);
}
