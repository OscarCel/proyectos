package com.example.prueba002.controller;

import com.example.prueba002.model.Videojuego;
import com.example.prueba002.service.VideojuegoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/videojuego")
public class VideojuegoController {

    @Autowired
    private VideojuegoService videojuegoService;


    //crea un nuevo videojuego
    @PostMapping("/crear")
    public long crear(@RequestBody @Valid Videojuego videojuego){
        if(videojuego.getId() != 0){
            
        }
        return 3l;
    }

    //muestra una lista con todos los videojuegos
    @GetMapping
    public List<Videojuego> list() {
        //return videojuegoService.list();
        List<Videojuego> videojuegos = new ArrayList<>();
        Videojuego juego1 = new Videojuego(1l, "The legend of zelda", false);
        videojuegos.add(juego1);
        Videojuego juego2 = new Videojuego(2l, "Super Mario", true);
        videojuegos.add(juego2);

        return videojuegos;
    }

    //muestra un videojuego identificado por id
    @GetMapping("/get/{id}")
    public Videojuego get(@PathVariable("id") long id) {
        //return videojuegoService.get(id);
        Videojuego videojuego = new Videojuego(1l, "The legend of zelda", false);
        return videojuego;
    }

    //modifica el contenido de un videojuego
    @PutMapping
    public void actualizar(@RequestBody Videojuego videojuego){
        
    }

    //elimina un videojuego
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        //videojuegoService.delete(id);
    }
}
