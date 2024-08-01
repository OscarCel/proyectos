package com.example.libros.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.libros.model.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long>{

}
