package es.cic.curso.ejerc005;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/saludo")
public class SaludosController {
    @Autowired
    private SaludosService saludosService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long crear(@RequestBody @Valid Saludo saludo){
        if(saludo.getId() != null){
            throw new RuntimeException("Me lo has tratado de colar");
        }
        return saludosService.crear(saludo);
    }

    @GetMapping("/{id}")
    public Saludo leer(@PathVariable("id") long id){
        return saludosService.leer(id);
    }

    @GetMapping
    public List<Saludo> listar(){
        return saludosService.listar();
    }

    @PutMapping
    public void actualizar(@RequestBody Saludo saludo){
        if(saludo.getId() == null){
            throw new RuntimeErrorException(null, "Me la trataste de colar");
        }
        saludosService.actualizar(saludo);
    }

    @DeleteMapping("/{id}")
    public void borraro(@PathVariable("id") long id){
        saludosService.borrar(id);
    }
}
