package es.cic.curso.ejerc005;

public class Saludo {
    private Long id;
    private String dniCifNie;
    private boolean escuchado;

    private String mensaje;


    public Saludo(){
        
    }

    public Saludo(Long id, String dniCifNie, boolean escuchado, String mensaje) {
        this.id = id;
        this.dniCifNie = dniCifNie;
        this.escuchado = escuchado;
        this.mensaje = mensaje;
    }


    /*
    public Saludo id(Long id){
        this.id = id;
        return this;
    }

    public Saludo dniCifNie(String dniCifNie){
        this.dniCifNie = dniCifNie;
        return this;
    }

    public Saludo escuchado(boolean escuchado){
        this.escuchado = escuchado;
        return this;
    }

    public Saludo mensaje(String mensaje){
        this.mensaje = mensaje;
        return this;
    }
    */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDniCifNie() {
        return dniCifNie;
    }

    public void setDniCifNie(String dniCifNie) {
        this.dniCifNie = dniCifNie;
    }

    public boolean isEscuchado() {
        return escuchado;
    }

    public void setEscuchado(boolean escuchado) {
        this.escuchado = escuchado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
