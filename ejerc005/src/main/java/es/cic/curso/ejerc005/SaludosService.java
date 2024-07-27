package es.cic.curso.ejerc005;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaludosService {
    @Autowired
    public SaludoRepository saludoRepository;
    public long crear(Saludo saludo){
        /*try{
            if(Math.random() < 0.5){
                throw new Exception();
            }
        }catch (Exception e){
            throw new SaludosException("Algo ha ido mal", e);
        } */       
        saludo = saludoRepository.save(saludo);
        return saludo.getId();
    }

    @Transactional(readOnly = true)
    public Saludo leer(){
        return new Saludo(1l, null, false, null);
    }
}
