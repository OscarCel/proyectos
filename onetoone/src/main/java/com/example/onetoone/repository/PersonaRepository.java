package com.example.onetoone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.onetoone.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>{

    @Query("SELECT p FROM Persona p WHERE p.nombre = :nombre")
    List<Persona> findByNombre(@Param("nombre") String nombre);
}
