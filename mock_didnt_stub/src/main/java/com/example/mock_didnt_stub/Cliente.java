package com.example.mock_didnt_stub;

public class Cliente {
    private Servicio servicio;

    public Cliente(Servicio servicio) {
        this.servicio = servicio;
    }

    public String procesarDato() {
        return "Procesado: " + servicio.obtenerDato();
    }
}