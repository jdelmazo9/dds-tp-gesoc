package grupo6.seguridad;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

public class ControladorDeUsuarios {
    private Set<Usuario> usuarios;
    private ControladorDeSeguridad controladorDeSeguridad; //@todo tiene un controlador de seguridad o lo hacemos standalone?

    public void agregarUsuario(String nombre, String contrasenia, RolUsuario rol) throws Exception {
        if(existeUsuario(nombre)){
            throw new Exception( "Ya existe un usuario con el nombre seleccionado" );
        }
        controladorDeSeguridad.validarUsuario(nombre); //@todo hacemos que lancen excepciones y las propagamos?
        controladorDeSeguridad.validarContrasenia(contrasenia); //@todo hacemos que lancen excepciones y las propagamos?
        usuarios.add(new Usuario(nombre, contrasenia, rol));
    }

    public void sacarUsuario(Usuario usuario, String contrasenia){
        if(usuario.validarConstrasenia(contrasenia))
            usuarios.remove(usuario);
    }

    private boolean existeUsuario(String nombre){
        return usuarios.stream().anyMatch(usuario -> usuario.getNombre().equals(nombre));
    }

    public Optional<Usuario> getUsuario(String nombre){
        return usuarios.stream().filter(usuario -> usuario.getNombre().equals(nombre)).findFirst();
    }

    public boolean validarUsuarioContrasenia(String nombre, String contrasenia){
        return this.getUsuario(nombre).
            map(usuario -> usuario.validarConstrasenia(contrasenia)).
            orElse(false);
    }
}
