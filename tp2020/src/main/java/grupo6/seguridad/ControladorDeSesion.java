package grupo6.seguridad;

import java.time.LocalTime;

public class ControladorDeSesion {
    private Usuario usuarioActivo;
    private LocalTime horaInicioSesion = null;
    private LocalTime horaFinSesion = null;
    private boolean hayUsuarioLogueado = false;
    private ControladorDeUsuarios controladorDeUsuarios; //@todo tiene un controlador de usuarios o lo hacemos standalone?

    public ControladorDeSesion(ControladorDeUsuarios cont){
        this.controladorDeUsuarios = cont;
    }

    public void logIn(String nombre, String contrasenia) throws Exception {
        if( hayUsuarioLogueado ){
            throw new Exception("Ya hay un usuario logueado");
        }
        else if( !controladorDeUsuarios.validarUsuarioContrasenia(nombre, contrasenia) ){
            System.out.println("El usuario y/o la contraseña ingresados no son correctos");
        }
        else{
            usuarioActivo = controladorDeUsuarios.getUsuario(nombre);
            horaInicioSesion = LocalTime.now();
            hayUsuarioLogueado = true;
        }
        
    }

    public void logOut(){
        if(hayUsuarioLogueado) {
            horaFinSesion = LocalTime.now();
            hayUsuarioLogueado = false;
        }
    }

    public boolean hayUsuarioLogueado(){
        return this.hayUsuarioLogueado;
    }

    public Usuario getUsuarioActivo(){
        return usuarioActivo;
    }

    public LocalTime getHoraInicioDeSesion(){
        return horaInicioSesion;
    }

    public LocalTime getHoraFinDeSesion(){
        return horaFinSesion;
    }
}
