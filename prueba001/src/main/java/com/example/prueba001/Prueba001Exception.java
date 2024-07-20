package com.example.prueba001;

public class Prueba001Exception extends RuntimeException{
    public Prueba001Exception(){
    }

    public Prueba001Exception(String mensaje){
        super(mensaje);
    }

    public Prueba001Exception(String mensaje, Throwable caused){
        super(mensaje, caused);
    }
}