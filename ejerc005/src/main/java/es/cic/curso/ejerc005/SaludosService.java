package es.cic.curso.ejerc005;

import org.springframework.stereotype.Service;

@Service
public class SaludosService {
    public String saludar(){
        try{
            if(Math.random() < 0.5){
                throw new Exception();
            }
        }catch (Exception e){
            throw new SaludosException("Algo ha ido mal", e);
        }        
        return this.toString();
    }
}
