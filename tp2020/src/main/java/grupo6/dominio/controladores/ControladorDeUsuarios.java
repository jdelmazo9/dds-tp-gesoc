package grupo6.dominio.controladores;

//import javax.swing.text.html.Option;

//import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import grupo6.seguridad.Excepciones.*;
import grupo6.dominio.repositorios.RepositorioDeUsuarios;
import grupo6.seguridad.RolUsuario;
import grupo6.seguridad.Usuario;

public class ControladorDeUsuarios {
    private Set<Usuario> usuarios = new HashSet<Usuario>();
    private ControladorDeSeguridad controladorDeSeguridad; //@todo tiene un controlador de seguridad o lo hacemos standalone?

    public ControladorDeUsuarios(ControladorDeSeguridad control){
        this.controladorDeSeguridad = control;
    }

    public void agregarUsuario(String nombre, String contrasenia, RolUsuario rol) throws Exception {
        
        if(RepositorioDeUsuarios.getInstancia().existe(nombre, contrasenia)){
            throw new NombreRepetidoException( "Ya existe un usuario con el nombre seleccionado" );
        }
        /*controladorDeSeguridad.validarUsuario(nombre); //@todo hacemos que lancen excepciones y las propagamos?
        controladorDeSeguridad.validarContrasenia(contrasenia); //@todo hacemos que lancen excepciones y las propagamos?
        */
        Usuario user = new Usuario(nombre, contrasenia, rol);
        RepositorioDeUsuarios.getInstancia().agregar(user);
        
        //usuarios.add(new Usuario(nombre, contrasenia, rol));
        
    }

    public void sacarUsuario(Usuario usuario, String contrasenia) throws ContraseniaInvalidaException {
        if(usuario.validarConstrasenia(contrasenia))
            usuarios.remove(usuario);
    }

    private boolean existeUsuario(String nombre){


        return usuarios.stream().anyMatch(usuario -> usuario.getNombre().equals(nombre));
    } 

    public Usuario getUsuario(String nombre) throws UsuarioInexistenteException {
        if(existeUsuario(nombre)){
            return usuarios.stream().filter(usuario -> usuario.getNombre().equals(nombre)).findFirst().get();
        }
        else{
            throw new UsuarioInexistenteException("El usuario no existe");
        }

    }

    public boolean  validarUsuarioContrasenia(String nombre, String contrasenia){
        Usuario user;
        boolean resultado;
        user = RepositorioDeUsuarios.getInstancia().buscarUsuario(nombre, contrasenia);
        if(user != null){
            try {
                 resultado = user.validarConstrasenia(contrasenia);
            } catch (ContraseniaInvalidaException e) {
                System.out.println(e);
                resultado = false;
            }
        }else{
            System.out.println("Usuario Incorrecto");
            resultado = false;
        }
            
        
        return resultado;
    }
}
