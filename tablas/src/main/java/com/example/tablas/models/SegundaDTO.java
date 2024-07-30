package com.example.tablas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SegundaDTO {
    private String apellido;

    @JsonIgnore
    private PrimeraDTO primeraDTO;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public PrimeraDTO getPrimeraDTO() {
        return primeraDTO;
    }

    public void setPrimeraDTO(PrimeraDTO primeraDTO) {
        this.primeraDTO = primeraDTO;
    }
    
}
