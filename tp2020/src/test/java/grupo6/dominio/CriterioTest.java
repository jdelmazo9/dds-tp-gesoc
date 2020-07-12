package grupo6.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

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

    @BeforeEach
    void setUp() {
        egresoTest = new OperacionDeEgreso();
        ingresoTest = new OperacionDeIngreso(1000, "Donacion");
        tamaño = new Criterio();
        pequeña = new Categoria("pequeña",tamaño);
        grande = new Categoria("grande",tamaño);//Al agregar el criterio tamaño a la categoria grande, se vincula en ambas entidades
        alcance = new Criterio();
        global = new Categoria("global",alcance);
        nacional = new Categoria("nacional",alcance);
        continental = new Categoria("continental",alcance);
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
}
