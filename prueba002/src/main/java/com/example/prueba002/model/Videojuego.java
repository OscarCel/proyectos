package com.example.prueba002.model;

import jakarta.validation.constraints.Size;

public class Videojuego {
    private Long id;
    @Size(min = 5)
    private String name;
    private boolean lanzado;

    //constructores
    public Videojuego() {
    }

    public Videojuego(Long id, String name, boolean lanzado) {
        this.id = id;
        this.name = name;
        this.lanzado = lanzado;
    }

    //getters y setters
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Videojuego other = (Videojuego) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

