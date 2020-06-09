package grupo6.utils;

import grupo6.seguridad.RolUsuario;
import grupo6.seguridad.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificadorValidadorLicitacionTest {

    Usuario usuario1, usuario2;
    NotificadorValidadorLicitacion notificador;

    @BeforeEach
    void setUp() {
        notificador = new NotificadorValidadorLicitacion();
        usuario1 = new Usuario("Cosme Fulanito","Fulanito123", RolUsuario.ESTANDAR);
        usuario2 = new Usuario("Lionel Messi","Messi123", RolUsuario.ESTANDAR);
        notificador.agregarRevisor(usuario1.getBandejaDeMensajes());
        notificador.agregarRevisor(usuario2.getBandejaDeMensajes());
    }

    //@org.junit.Test
    @Test
    void testBandejaMensajes(){
        notificador.notificar("Hola");
        notificador.notificar("Mundo");
        assertEquals(2, usuario1.getBandejaDeMensajes().cantidadMensajes());
        assertEquals(2, usuario2.getBandejaDeMensajes().cantidadMensajes());
        assertEquals("Hola", usuario1.getBandejaDeMensajes().obtenerMensajes().get(0).leerMensaje());
        assertEquals("Mundo", usuario2.getBandejaDeMensajes().obtenerMensajes().get(1).leerMensaje());
    }
}
