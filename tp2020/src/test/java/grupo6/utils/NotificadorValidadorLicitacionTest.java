package grupo6.utils;

import grupo6.dominio.ResultadoValidacion;
import grupo6.seguridad.RolUsuario;
import grupo6.seguridad.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificadorValidadorLicitacionTest {

    Usuario usuario1, usuario2;
    NotificadorValidadorLicitacion notificador;
    ResultadoValidacion resultado1;
    ResultadoValidacion resultado2;


    @BeforeEach
    void setUp() {
        notificador = new NotificadorValidadorLicitacion();
        usuario1 = new Usuario("Cosme Fulanito","Fulanito123", RolUsuario.ESTANDAR);
        usuario2 = new Usuario("Lionel Messi","Messi123", RolUsuario.ESTANDAR);
        notificador.agregarRevisor(usuario1.getBandejaDeMensajes());
        notificador.agregarRevisor(usuario2.getBandejaDeMensajes());
        resultado1 = new ResultadoValidacion();
        resultado2 = new ResultadoValidacion();
        resultado1.set_mensaje_resultado("Hola");
        resultado2.set_mensaje_resultado("Mundo");

    }

    //@org.junit.Test
    @Test
    void testBandejaMensajes(){
        notificador.notificar(resultado1);
        notificador.notificar(resultado2);
        assertEquals(2, usuario1.getBandejaDeMensajes().cantidadMensajes());
        assertEquals(2, usuario2.getBandejaDeMensajes().cantidadMensajes());
        assertEquals("Hola", usuario2.getBandejaDeMensajes().obtenerMensajes().get(0).get_mensaje());
        assertEquals("Mundo", usuario2.getBandejaDeMensajes().obtenerMensajes().get(1).get_mensaje());
    }
}
