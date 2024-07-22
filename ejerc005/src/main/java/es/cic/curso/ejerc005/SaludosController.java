package es.cic.curso.ejerc005;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saludo")
public class SaludosController {
    //@Autowired
    //private SaludosService saludosService;


    @PostMapping
    public long crear(@RequestBody Saludo saludo){
        throw new UnsupportedOperationException("No implementado aun");
    }

    @GetMapping("/{id}")
    public Saludo leer(@PathVariable("id") long id){
        throw new UnsupportedOperationException("No implementado aun");
    }

    @GetMapping
    public List<Saludo> listar(){
        throw new UnsupportedOperationException("No implementado aun");
    }

    @PutMapping
    public void actualizar(@RequestBody Saludo saludo){
        throw new UnsupportedOperationException("No implementado aun");
    }

    @DeleteMapping("/{id}")
    public void borraro(@PathVariable("id") long id){
        throw new UnsupportedOperationException("No implementado aun");
    }
}
