package com.example.tablas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.tablas.Repository.ExpedienteRepository;
import com.example.tablas.models.Expediente;

@SpringBootApplication
public class TablasApplication{

    public static void main(String[] args) {
        SpringApplication.run(TablasApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner commandLineRunner(ExpedienteRepository expedienteRepository){
        return args -> {
            Expediente expediente = new Expediente();
            expediente.setNombre("Juan");
    
            expedienteRepository.save(expediente);
        };
    }*/
    

}