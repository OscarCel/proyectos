package com.example.prueba002.controller;

import com.example.prueba002.model.Videojuego;
import com.example.prueba002.service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/videojuego")
public class VideojuegoController {

    //@Autowired
    //private VideojuegoService videoGameService;


    @PostMapping
    public long crear(@RequestBody Videojuego videojuego){
        if(videojuego.getId() != 0){
            
        }
        return 1l;
    }

    @GetMapping
    public List<Videojuego> list() {
        //return videoGameService.list();
        List<Videojuego> videojuegos = new ArrayList<>();
        Videojuego juego1 = new Videojuego(1l, "The legend of zelda", false);
        videojuegos.add(juego1);
        Videojuego juego2 = new Videojuego(2l, "Super Mario", true);
        videojuegos.add(juego2);

        return videojuegos;
    }

    @GetMapping("/get/{id}")
    public Videojuego get(@PathVariable("id") Long id) {
        //return videoGameService.get(id);
        Videojuego videojuego = new Videojuego(1l, "The legend of zelda", false);
        return videojuego;
    }

    @PutMapping
    public void actualizar(@RequestBody Videojuego videojuego){
        throw new UnsupportedOperationException("No implementado aun");
    }

    /*@DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        videoGameService.delete(id);
    }*/
}
