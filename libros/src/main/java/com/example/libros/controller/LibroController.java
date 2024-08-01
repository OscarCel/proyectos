package com.example.libros.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.libros.model.Libro;
import com.example.libros.service.LibroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libro")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long crear(@RequestBody @Valid Libro libro){
        if(libro.getId() != null){
            throw new RuntimeException("No valido");
        }
        return libroService.crear(libro);
    }

    @GetMapping("/{id}")
    public Libro leer(@PathVariable("id") long id){
        return libroService.leer(id);
    }

    @GetMapping
    public List<Libro> listar(){
        return libroService.listar();
    }

    @PutMapping
    public void actualizar(@RequestBody Libro libro){
        if(libro.getId() == null){
            throw new RuntimeErrorException(null, "No valido");
        }
        libroService.actualizar(libro);
    }

    @DeleteMapping("/{id}")
    public void borraro(@PathVariable("id") long id){
        libroService.borrar(id);
    }

}
