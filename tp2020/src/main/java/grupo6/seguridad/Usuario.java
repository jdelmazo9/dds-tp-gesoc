package grupo6.seguridad;

import org.mindrot.jbcrypt.BCrypt;

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

    public boolean validarConstrasenia(String contrasenia){
        return BCrypt.checkpw(contrasenia, contraseniaHash);
    }
}
