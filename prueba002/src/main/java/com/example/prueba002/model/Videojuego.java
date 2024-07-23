package com.example.prueba002.model;

public class Videojuego {
    private Long id;
    private String name;
    private boolean lanzado;

    public Videojuego() {
    }

    public Videojuego(Long id, String name, boolean lanzado) {
        this.id = id;
        this.name = name;
        this.lanzado = lanzado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLanzado() {
        return lanzado;
    }

    public void setLanzado(boolean lanzado) {
        this.lanzado = lanzado;
    }
}

