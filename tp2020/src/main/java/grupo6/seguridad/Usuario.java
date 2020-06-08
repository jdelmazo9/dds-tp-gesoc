package grupo6.seguridad;

import org.mindrot.jbcrypt.BCrypt;

import grupo6.seguridad.Excepciones.ContraseniaInvalidaException;

public class Usuario {
    private String nombre;
    private String contraseniaHash;
    private RolUsuario rol;

    public Usuario(String nombre, String contrasenia, RolUsuario rol){
        this.nombre = nombre;
        this.rol = rol;
        this.setContrasenia(contrasenia);
    }

    public void setContrasenia(String contrasenia){
        contraseniaHash = BCrypt.hashpw(contrasenia, BCrypt.gensalt());
    }

    public String getNombre() {
        return nombre;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public boolean validarConstrasenia(String contrasenia) throws ContraseniaInvalidaException {
        if(BCrypt.checkpw(contrasenia, contraseniaHash)){
            return true;
        }
        else{
            throw new ContraseniaInvalidaException("Contraseña incorrecta");
        }
    }
}
