package grupo6.dominio;

import grupo6.vinculaciones.ComparadorFecha;
import grupo6.vinculaciones.ComparadorValor;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VinculacionTest {

    static OperacionDeIngreso i1, i2, i3;
    static OperacionDeEgreso e1, e2, e3;
    static Item microondas, heladera, lavarropas;
    static ArrayList<OperacionDeIngreso> ingresos;
    static ArrayList<OperacionDeEgreso> egresos;

    @BeforeAll
    public static void init() {
        //INSTANCIO OPERACIONES DE INGRESO
        i1 = new OperacionDeIngreso("Operacion 1", 3500d, LocalDate.parse("2018-02-27"));
        i2 = new OperacionDeIngreso("Operacion 2", 4000d, LocalDate.parse("2018-02-22"));
        i3 = new OperacionDeIngreso("Operacion 3", 2500d, LocalDate.parse("2018-02-23"));

        //INSTANCIO ITEMS PARA OPERACIONES DE EGRESO
        microondas = new Item(TipoItem.Articulo, "Microondas marca Samsung", 1200.0);
        heladera = new Item(TipoItem.Articulo, "Heladera marca Hp", 1700.00);
        lavarropas = new Item(TipoItem.Articulo, "Lavarropas marca Hp", 1800.00);

        //INSTANCIO OPERACIONES DE EGRESO
        e1 = new OperacionDeEgreso(LocalDate.parse("2018-03-28"));
        e2 = new OperacionDeEgreso(LocalDate.parse("2018-03-30"));
        e3 = new OperacionDeEgreso(LocalDate.parse("2018-03-29"));

        e1.agregarItem(microondas);
        e2.agregarItem(heladera);
        e3.agregarItem(lavarropas);

        //Creo las listas de ingresos y egresos
        ingresos = new ArrayList<OperacionDeIngreso>() {
            {
                add(i1);
                add(i2);
                add(i3);
            }
        };

        egresos = new ArrayList<OperacionDeEgreso>() {
            {
                add(e1);
                add(e2);
                add(e3);
            }
        };
    }

    @Test
    void vinculacionesPorValorTest() {
        AdaptadorVinculadorConcreto vinculador = new AdaptadorVinculadorConcreto();
        ArrayList<Vinculacion> vinculaciones = vinculador.vincular(ingresos, egresos, CriteriosEnum.CRITERIO_VALOR_PRIMERO_EGRESO);

        ArrayList<Vinculacion> vinculacionesEsperadas = new ArrayList<>(
            Arrays.asList(
                new Vinculacion(3,1, 1200d),
                new Vinculacion(3,2,1300d),
                new Vinculacion(1, 2, 400d),
                new Vinculacion(1, 3, 1800d)
            )
        );

        assertArrayEquals(vinculacionesEsperadas.toArray(), vinculaciones.toArray());
    }


    @Test
    void vinculacionesPorFechaTest() {
        AdaptadorVinculadorConcreto vinculador = new AdaptadorVinculadorConcreto();
        ArrayList<Vinculacion> vinculaciones = vinculador.vincular(ingresos, egresos, CriteriosEnum.CRITERIO_FECHA);

        ArrayList<Vinculacion> vinculacionesEsperadas = new ArrayList<>(
            Arrays.asList(
                new Vinculacion(2,1, 1200d),
                new Vinculacion(2,3,1800d),
                new Vinculacion(2, 2, 1000d),
                new Vinculacion(3, 2, 700d)
            )
        );

        assertArrayEquals(vinculacionesEsperadas.toArray(), vinculaciones.toArray());
    }

}
