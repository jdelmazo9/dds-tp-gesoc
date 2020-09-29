package grupo6.dominio.controladores;

import grupo6.seguridad.Validacion;

public class ControladorDeSeguridad {
    private Validacion validadorContrasenia;
    private Validacion validadorUsuario;

    public ControladorDeSeguridad(Validacion validadorContrasenia, Validacion validadorUsuario){
        this.validadorContrasenia = validadorContrasenia;
        this.validadorUsuario = validadorUsuario;
    }

    public boolean validarContrasenia(String contrasenia){
        return validadorContrasenia.validar(contrasenia);
    }

    public boolean validarUsuario(String usuario){
        return validadorUsuario.validar(usuario);
    }
}
