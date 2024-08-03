package com.example.onetoone.controller;

import java.util.List;

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

import com.example.onetoone.model.Persona;
import com.example.onetoone.service.PersonaService;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> listar(){
        return personaService.listar();
    }

    @GetMapping("/{id}")
    public Persona leer(@PathVariable("id") long id){
        return personaService.leer(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Persona crear(@RequestBody Persona persona){
        personaService.crear(persona);
        return persona;
    }

    @PutMapping
    public Persona actualizar(@RequestBody Persona persona){
        personaService.actualizar(persona);
        return persona;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable long id){
        Persona persona = personaService.leer(id);
        personaService.borrar(persona);
    }
}
