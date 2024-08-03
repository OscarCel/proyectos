package com.example.tablas.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoEncontradoException extends RuntimeException{

    private long id;

    public NoEncontradoException(){

    }

    public NoEncontradoException(String message, long id){
        super(message);
        this.id = id;
    }

    public NoEncontradoException(String message, Throwable th, long id){
        super(message, th);
    }
}
