package com.example.libros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.libros.model.Libro;
import com.example.libros.repository.LibroRepository;

@Service
@Transactional
public class LibroService {
    @Autowired
    public LibroRepository libroRepository;

    public long crear(Libro libro){
        libro = libroRepository.save(libro);
        return libro.getId();
    }

    @Transactional(readOnly = true)
    public List<Libro> listar(){
        List<Libro> result = new ArrayList<>();
        result.forEach(result::add);
        return (result);
    }

    @Transactional(readOnly = true)
    public Libro leer(long id){
        Optional<Libro> posibleResultado = libroRepository.findById(id);

        return posibleResultado.orElse(null);
    }

    public void actualizar(Libro libro){
        libroRepository.save(libro);
    }

    public void borrar(long id){
        libroRepository.deleteById(id);
    }

}
