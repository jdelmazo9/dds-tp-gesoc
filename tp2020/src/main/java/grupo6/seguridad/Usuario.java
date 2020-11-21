package grupo6.seguridad;

import grupo6.dominio.entidades.EntidadPersistente;
import grupo6.dominio.entidades.BandejaDeMensajes;
import org.mindrot.jbcrypt.BCrypt;

import grupo6.seguridad.Excepciones.ContraseniaInvalidaException;

import javax.persistence.*;

@Entity
public class Usuario extends EntidadPersistente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private String contraseniaHash;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

    @OneToOne
    private BandejaDeMensajes bandejaDeMensajes;

    public Usuario(String nombre, String contrasenia, RolUsuario rol){
        this.nombre = nombre;
        this.rol = rol;
        this.setContrasenia(contrasenia);
        System.out.println(this.nombre+". Mi contrasenia es: "+ this.contraseniaHash);
        this.bandejaDeMensajes = new BandejaDeMensajes();
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

    public BandejaDeMensajes getBandejaDeMensajes() {
        return bandejaDeMensajes;
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
