package com.example.prueba001;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Prueba001Controller {

    @Autowired
    private Prueba001Service prueba001service;

    @GetMapping("/prueba001")
    public String prueba001(){
        return prueba001service.service();
    }
}
