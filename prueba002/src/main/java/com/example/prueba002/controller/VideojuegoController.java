package com.example.prueba002.controller;

import com.example.prueba002.model.Videojuego;
import com.example.prueba002.service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuego")
public class VideojuegoController {

    @Autowired
    private VideojuegoService videoGameService;

    @GetMapping
    public List<Videojuego> list() {
        return videoGameService.list();
    }

    @GetMapping("/get/{id}")
    public Videojuego get(@PathVariable("id") int id) {
        return videoGameService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        videoGameService.delete(id);
    }
}
