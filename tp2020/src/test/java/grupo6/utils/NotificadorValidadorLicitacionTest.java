package grupo6.utils;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class NotificadorValidadorLicitacionTest {

    BandejaDeMensajes bandeja1, bandeja2;
    NotificadorValidadorLicitacion notificador;

    @BeforeEach
    void setUp() {
        notificador = new NotificadorValidadorLicitacion();
        bandeja1 = new BandejaDeMensajes();
        bandeja2 = new BandejaDeMensajes();
        notificador.agregarRevisor(bandeja1);
        notificador.agregarRevisor(bandeja2);
    }

    @Test
    void testBandejaMensajes(){
        notificador.notificar("Hola");
        notificador.notificar("Mundo");
        assertEquals(2, bandeja1.cantidadMensajes());
        assertEquals(2, bandeja2.cantidadMensajes());
        assertEquals("Hola", bandeja1.obtenerMensajes().get(0));
        assertEquals("Mundo", bandeja2.obtenerMensajes().get(1));
    }
}
