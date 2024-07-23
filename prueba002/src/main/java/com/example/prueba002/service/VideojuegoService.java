package com.example.prueba002.service;

import com.example.prueba002.model.Videojuego;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideojuegoService {

    private List<Videojuego> Videojuego = new ArrayList<>();
    private long nextId = 1;

    public VideojuegoService() {
        Videojuego.add(new Videojuego(nextId++, "The Legend of Zelda", false));
        Videojuego.add(new Videojuego(nextId++, "Super Mario Bros", true));
    }

    public List<Videojuego> list() {
        return new ArrayList<>(Videojuego);
    }

    public Videojuego get(long id) {
        return Videojuego.stream().filter(vg -> vg.getId() == id).findFirst().orElse(null);
    }

    public void delete(long id) {
        Videojuego.removeIf(vg -> vg.getId() == id);
    }
}
