package grupo6.dominio;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import grupo6.seguridad.RolUsuario;
import grupo6.seguridad.Usuario;


class ValidarEgresoTest {

    private Usuario usuario;
    private OperacionDeEgreso compraTest;
    private Presupuesto presupuesto1;
    private Presupuesto presupuesto2;
    private ArrayList<Item> items1;
    private ArrayList<Item> items2;
    private Proveedor proovedor1;
    private Proveedor proovedor2;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Diego Armando", "asdas1Adasd#",RolUsuario.ADMIN);
        Item microondas1 = new Item(TipoItem.Articulo, "Microondas marca Huawei", 1200.0);
        Item heladera1 = new Item(TipoItem.Articulo, "Heladera marca Hp", 1700.00);
        items1 = new ArrayList<Item>();
        items2 = new ArrayList<Item>();
        items1.add(microondas1);
        items1.add(heladera1);
        Item microondas2 = new Item(TipoItem.Articulo, "Microondas marca Samsung", 1800.0);
        Item heladera2 = new Item(TipoItem.Articulo, "Heladera marca FrioMax", 1800.00);
        items2.add(microondas2);
        items2.add(heladera2);
        proovedor1 = new Proveedor("Juan Pablo Segundo", 20204523, "Calle falsa 123");
        proovedor2 = new Proveedor("Benedicto XVI", 20201919, "Vaticano 1");
        presupuesto1 = new Presupuesto(items1, 2900, proovedor1);
        presupuesto2 = new Presupuesto(items2, 3600, proovedor2);
        compraTest = new OperacionDeEgreso();
        compraTest.agregarItem(heladera1);
        compraTest.agregarItem(microondas1);
        compraTest.setProveedor(proovedor1);
        compraTest.agregarPresupuesto(presupuesto1);
        compraTest.agregarPresupuesto(presupuesto2);
        compraTest.suscribirComoRevisor(usuario.getBandejaDeMensajes());
    }

    @Test
    void validacion() {
        compraTest.validarLicitacion();
        ResultadoValidacion resultado = usuario.getBandejaDeMensajes().obtenerMensajes().get(0);
        assertTrue(resultado.validacionOK);
    }
    @Test
    void noHaySuficientesPresupuestos(){
        ValidadorLicitacion validador = compraTest.get_validador();
        validador.set_cantidadPresupuestos(5);
        compraTest.validarLicitacion();
        ResultadoValidacion resultado = usuario.getBandejaDeMensajes().obtenerMensajes().get(0);
        assertEquals("No se satisface la cantidad de presupuestos requeridos",resultado.get_mensaje());
    }

}