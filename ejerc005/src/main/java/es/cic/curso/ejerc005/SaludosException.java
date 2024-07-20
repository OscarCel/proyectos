package es.cic.curso.ejerc005;

public class SaludosException extends RuntimeException{
    public SaludosException(){
    }

    public SaludosException(String mensaje){
        super(mensaje);
    }

    public SaludosException(String mensaje, Throwable caused){
        super(mensaje, caused);
    }
}
