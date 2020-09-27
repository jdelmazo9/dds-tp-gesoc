package grupo6.spark.utils;

import grupo6.dominio.entidades.ResultadoValidacion;
import grupo6.seguridad.RolUsuario;
import grupo6.seguridad.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        resultado1 = new ResultadoValidacion(0);
        resultado2 = new ResultadoValidacion(0);
        resultado1.set_mensaje_resultado("Hola");
        resultado2.set_mensaje_resultado("Mundo");

    }

    //@org.junit.Test
    @Test
    void testBandejaMensajes(){
        notificador.notificar(resultado1);
        notificador.notificar(resultado2);
        Assertions.assertEquals(2, usuario1.getBandejaDeMensajes().cantidadMensajes());
        Assertions.assertEquals(2, usuario2.getBandejaDeMensajes().cantidadMensajes());
        Assertions.assertEquals("Hola", usuario2.getBandejaDeMensajes().obtenerMensajes().get(0).get_mensaje());
        Assertions.assertEquals("Mundo", usuario2.getBandejaDeMensajes().obtenerMensajes().get(1).get_mensaje());
    }
}
