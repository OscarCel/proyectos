package com.example.onetoone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.onetoone.model.Persona;
import com.example.onetoone.repository.PersonaRepository;

@Service
@Transactional
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Transactional(readOnly = true)
    public List<Persona> listar(){
        return personaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Persona leer(long id){
        return personaRepository.findById(id).orElseThrow(() -> new NoEncontradoException(String.format("No lo encontre el %d chaval@", id), id));
    }

    public void crear (Persona persona){
        personaRepository.save(persona);
    }

    public void actualizar(Persona persona){
        personaRepository.save(persona);
    }

    public void borrar(Persona persona){
        personaRepository.delete(persona);
    }
}
