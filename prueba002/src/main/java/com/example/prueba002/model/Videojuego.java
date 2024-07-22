package com.example.prueba002.model;

public class Videojuego {
    private int id;
    private String name;

    public Videojuego() {
    }

    public Videojuego(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

