package grupo6.seguridad;

import java.time.LocalTime;

public class ControladorDeSesion {
    private Usuario usuarioActivo;
    private LocalTime horaInicioSesion = null;
    private LocalTime horaFinSesion = null;
    private boolean hayUsuarioLogueado = false;
    private ControladorDeUsuarios controladorDeUsuarios; //@todo tiene un controlador de usuarios o lo hacemos standalone?

    public void logIn(String nombre, String contrasenia) throws Exception {
        if( hayUsuarioLogueado )
            throw new Exception("Ya hay un usuario logueado");
        if( !controladorDeUsuarios.validarUsuarioContrasenia(nombre, contrasenia) )
            throw new Exception("El usuario y/o la contraseña ingresados no son correctos");
        usuarioActivo = controladorDeUsuarios.getUsuario(nombre).get();
        horaInicioSesion = LocalTime.now();
        hayUsuarioLogueado = true;
    }

    public void logOut(){
        if(hayUsuarioLogueado) {
            horaFinSesion = LocalTime.now();
            hayUsuarioLogueado = false;
        }
    }
}
