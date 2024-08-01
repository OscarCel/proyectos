package com.example.tablas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tablas.Repository.ExpedienteRepository;
import com.example.tablas.models.Expediente;

@Service
@Transactional
public class ExpedienteService {

    @Autowired
    private ExpedienteRepository expedienteRepository;
    
    @Transactional(readOnly = true)
    public List<Expediente> listar() {
        return expedienteRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Expediente mostrar(long id){
        return expedienteRepository.findById(id).orElseThrow(() -> new RuntimeException("No lo encontre"));
    }

    public void crear(Expediente expediente){
        expedienteRepository.save(expediente);
    }

    public void actualizar(Expediente expediente){
        expedienteRepository.save(expediente);
    }

    public void borrar(Expediente expediente){
        expedienteRepository.delete(expediente);
    }
}
