package com.example.libros;

public class LibroException extends RuntimeException{

    public LibroException(){
    }

    public LibroException(String mensaje){
        super(mensaje);
    }

    public LibroException(String mensaje, Throwable caused){
        super(mensaje, caused);
    }
}
