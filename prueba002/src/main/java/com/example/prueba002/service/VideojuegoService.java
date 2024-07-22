package com.example.prueba002.service;

import com.example.prueba002.model.Videojuego;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideojuegoService {

    private List<Videojuego> Videojuego = new ArrayList<>();
    private int nextId = 1;

    public VideojuegoService() {
        Videojuego.add(new Videojuego(nextId++, "The Legend of Zelda"));
        Videojuego.add(new Videojuego(nextId++, "Super Mario Bros"));
    }

    public List<Videojuego> list() {
        return new ArrayList<>(Videojuego);
    }

    public Videojuego get(int id) {
        return Videojuego.stream().filter(vg -> vg.getId() == id).findFirst().orElse(null);
    }

    public void delete(int id) {
        Videojuego.removeIf(vg -> vg.getId() == id);
    }
}
