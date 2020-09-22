package grupo6.dominio;

import grupo6.dominio.entidades.Categoria;
import grupo6.dominio.entidades.Criterio;
import grupo6.dominio.entidades.OperacionDeEgreso;
import grupo6.dominio.entidades.OperacionDeIngreso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CriterioTest {
    OperacionDeEgreso egresoTest;
    OperacionDeIngreso ingresoTest;
    Criterio tamaño;
    Categoria pequeña;
    Categoria grande;
    Criterio alcance; //Será padre de tamaño
    Categoria global;
    Categoria nacional;
    Categoria continental;
    Criterio provincia;

    @BeforeEach
    void setUp() {
        egresoTest = new OperacionDeEgreso();
        ingresoTest = new OperacionDeIngreso("Donacion", 1000d);
        tamaño = new Criterio();
        pequeña = new Categoria("pequeña",tamaño);
        grande = new Categoria("grande",tamaño);//Al agregar el criterio tamaño a la categoria grande, se vincula en ambas entidades
        alcance = new Criterio();
        global = new Categoria("global",alcance);
        nacional = new Categoria("nacional",alcance);
        continental = new Categoria("continental",alcance);
        provincia = new Criterio();
    }
    @Test
    void categoriasAutoVinculadas(){
        assertEquals(tamaño.getCategorias().size(),2);
    }

    @Test
    void criterioSinHijosNoReconocidos(){
        tamaño.agregarPadre(alcance);
        assertEquals(alcance.getHijo(),tamaño);
    }

    @Test
    void categoriaYaAsignadaSinCambiosAlAsignar(){
        grande.vincularCriterio(alcance);
        assertEquals(alcance.getCategorias().size(),3);
        assertEquals(grande.getCriterio(),tamaño); //no se vincula porque el Criterio grande ya está vinculado con Tamaño
    }

    @Test
    void categoriaLimpiaSePuedeAsignar(){
        grande.limpiar();
        grande.vincularCriterio(alcance);
        assertEquals(alcance.getCategorias().size(),4);
        assertEquals(grande.getCriterio(),alcance); //luego de limpiarlo se puede vincular a otro Criterio
    }

    @Test
    void padreNoPuedeTenerMasDeUnHijo(){
        alcance.agregarHijo(tamaño);
        alcance.agregarHijo(provincia);
        assertEquals(alcance.getHijo(),tamaño); //No se puede agregar un segundo criterio hijo, por ende se mantiene el primero asignado
    }

    @Test
    void padreReemplazaHijo(){
        alcance.agregarHijo(tamaño);
        alcance.limpiarHijo();
        alcance.agregarHijo(provincia);
        assertEquals(alcance.getHijo(),provincia);
        assertEquals(tamaño.getPadre(),null);//Se reemplaza el hijo. El padre tiene nuevo hijo, y el primer hijo queda sin padre
    }
}
