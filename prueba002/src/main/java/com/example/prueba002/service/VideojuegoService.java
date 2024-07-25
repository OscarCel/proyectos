package com.example.prueba002.service;

import com.example.prueba002.model.Videojuego;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideojuegoService {

    //creo una lista de videojuegos
    private List<Videojuego> Videojuegos = new ArrayList<>();
    private long Id = 1;

    //creo los videojuegos y los añado a la lista
    public VideojuegoService() {
        Videojuegos.add(new Videojuego(Id++, "The Legend of Zelda", false));
        Videojuegos.add(new Videojuego(Id++, "Super Mario Bros", true));
    }

    //muestro una lista de los videojuegos
    public List<Videojuego> list() {
        return new ArrayList<>(Videojuegos);
    }

    //muestra un videojuego identificado por id
    public Videojuego get(long id) {
        return Videojuegos.stream()
        .filter(juego -> juego.getId().equals(id))
        .findFirst()
        .orElse(null);
    }

    //elimina un videojuego de la lista
    public void delete(long id) {
        Videojuegos.removeIf(juego -> juego.getId().equals(id));
    }
}
