package grupo6.dominio;

import grupo6.dominio.entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EgresoTest {

    Item microondas, heladera;
    Proveedor juancito;
    OperacionDeEgreso compraTest;
    DocumentoComercial factura;
    LocalDate unaFecha;
    URL link;

    @BeforeEach
    void setUp() {
        microondas = new Item(TipoItem.Articulo, "Microondas marca Samsung", 1200.0);
        heladera = new Item(TipoItem.Articulo, "Heladera marca Hp", 1700.00);
        juancito = new Proveedor("Juan Pablo Segundo", 20204523, "Calle falsa 123");
        factura = new DocumentoComercial(TipoDocumento.Factura, 420423042);
        compraTest = new OperacionDeEgreso();
        compraTest.adjuntar(factura);
        compraTest.agregarItem(heladera);
        compraTest.agregarItem(microondas);
        compraTest.setProveedor(juancito);
        //unaFecha = new Date();
        unaFecha = LocalDate.now();
    }

    @Test
    void calcularValorTotalOperacionEgreso() {
        assertEquals(2900.00, compraTest.getValorTotal(), "Heladera+Microondas");
        compraTest.eliminarItem(heladera);
        assertEquals(1200.00, compraTest.getValorTotal(), "Al eliminar o quitar un item, se vuelve a actualizar la variable ValorTotal automáticamente");
    }
    @Test
    void operacionDeEgresoInstanciaFechaActual() {
        //unaFecha.getTime(); //Fecha de hoy
        assertEquals(unaFecha, compraTest.getFecha(),"Se comprueba que al crear una fecha de egreso se instancia la fecha del día actual.");
    }
    @Test
    void docComercialExterno() throws MalformedURLException {
        link = new URL("https://www.enersa.com.ar/wp-content/uploads/2018/02/Pago-Banelco-_2_.pdf");
        compraTest.adjuntar(link);
        assertEquals(link,compraTest.getDocComercialExterno());
    }
}
