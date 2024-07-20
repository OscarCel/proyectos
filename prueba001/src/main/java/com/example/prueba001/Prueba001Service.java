package com.example.prueba001;

import org.springframework.stereotype.Service;

@Service
public class Prueba001Service {
    public String service(){
        try{
            if(Math.random() < 0.5){
                throw new Exception();
            }
        }catch (Exception e){
            throw new Prueba001Exception("Algo ha ido mal", e);
        }      

        String mensaje;
        if(Math.random() < 0.5){
            mensaje = "Hola mundo\n";
        }else{
            mensaje = "Adios mundo\n";
        }

        return mensaje;
    }
}
