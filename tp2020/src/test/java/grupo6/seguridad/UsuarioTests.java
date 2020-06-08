package grupo6.seguridad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import grupo6.seguridad.Excepciones.*;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTests {

    Usuario user;
    ValidacionRegEx regex;
    ValidacionLongitud longi;
    ControladorDeSeguridad controlador_segu;
    ControladorDeUsuarios controlador_usuario;
    ControladorDeSesion controlador_sesion;

    @BeforeEach
    void setUp() throws Exception {
        regex = new ValidacionRegEx();
        longi = new ValidacionLongitud(4);
        user = new Usuario("Carlos", "asdas1Adasd#",RolUsuario.ADMIN);
        controlador_segu = new ControladorDeSeguridad(regex, longi);
        controlador_usuario = new ControladorDeUsuarios(controlador_segu);
        controlador_sesion = new ControladorDeSesion(controlador_usuario);
        controlador_usuario.agregarUsuario(user.getNombre(), "asdas1Adasd#", user.getRol());
    }

    @Test
    void LogInTest() throws Exception {
        assertThrows(ContraseniaInvalidaException.class, () -> {
            user.validarConstrasenia("contraseniaErronea");}
            );
        assertThrows(UsuarioInexistenteException.class, () -> {
            controlador_usuario.getUsuario("Carla");}
            );
        assertThrows(NombreRepetidoException.class, () -> {
            controlador_usuario.agregarUsuario("Carlos", "asdas1Adasd#", RolUsuario.ESTANDAR);});
        
        controlador_sesion.logIn("Carlos", "asdas1Adasd#");
        assertTrue(controlador_sesion.hayUsuarioLogueado());

        controlador_sesion.logOut();
        assertFalse(controlador_sesion.hayUsuarioLogueado());

    }

}
