package com.example.tablas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.tablas.models.Expediente;

public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {

    @Query("SELECT e FROM Expediente e WHERE e.nombre = :nombre")
    List<Expediente> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT e FROM Expediente e WHERE e.id = :id")
    Expediente findByID(@Param("id") Long id);

}
