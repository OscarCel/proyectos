package com.example.tablas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tablas.models.Expediente;

public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {
}
