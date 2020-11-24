package grupo6.dominio.entidades;

import com.google.gson.Gson;
import grupo6.seguridad.Usuario;

import java.time.LocalTime;

public class Sesion {
    private Usuario usuario;
    private LocalTime horaInicioSesion = null;
    private LocalTime horaFinSesion = null;

    public Sesion(Usuario usuario){
        this.usuario = usuario;
        horaInicioSesion = LocalTime.now();
    }

    public static Sesion sesionFromJson(String string){
        return new Gson().fromJson(string, Sesion.class);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalTime getHoraFinSesion() {
        return horaFinSesion;
    }

    public void setHoraFinSesion(LocalTime horaFinSesion) {
        this.horaFinSesion = horaFinSesion;
    }

    public LocalTime getHoraInicioSesion() {
        return horaInicioSesion;
    }

    public void setHoraInicioSesion(LocalTime horaInicioSesion) {
        this.horaInicioSesion = horaInicioSesion;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
