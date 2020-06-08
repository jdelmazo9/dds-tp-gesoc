package grupo6.utils;

import java.time.LocalDate;

public class Mensaje {
    private String contenido;
    private LocalDate fecha;
    private boolean leido;

    public Mensaje(String contenido){
        this.contenido = contenido;
        this.fecha = LocalDate.now();
        leido = false;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String leerMensaje() {
        leido = true;
        return contenido;
    }

    public boolean estaLeido(){
        return leido;
    }
}
