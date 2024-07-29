package com.example.tablas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tablas.models.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
