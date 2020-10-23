package grupo6.dominio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import grupo6.dominio.entidades.*;
import grupo6.seguridad.RolUsuario;
import grupo6.seguridad.Usuario;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class PersistenceTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager = null;
    private Usuario operacionDeEgreso = new Usuario("Cosme", "1234", RolUsuario.ADMIN);
    OperacionDeEgreso compraTest;
    Presupuesto presupuestoTest;

    @BeforeClass
    public static void setUpClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("db");
        //hbm2ddl.auto - create | update | validate | create-drop

    }

    /*
    private  <T> void borrarObjetosDeClase(Class<T> class1) {
        this.entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaDelete<T> query = builder.createCriteriaDelete(class1);
        query.from(class1);
        this.entityManager.createQuery(query).executeUpdate();
        transaction.commit();
    }*/


    @Before
    public void setUp() {
//	        borrarObjetosDeClase(Casa.class        );
//	        this.casaExistente = new Casa("CasaExistente");
//			this.entityManager.getTransaction().begin();
//			this.entityManager.persist(casaExistente);
//			this.entityManager.getTransaction().commit();
    }

    @Test
    public void testGuardarCasa() {
        Item microondas = new Item(TipoItem.Articulo, "Microondas marca Samsung", 1200.0);
        Item microondas2 = new Item(TipoItem.Articulo, "Microondas marca Samsung", 1200.0);
        Item heladera = new Item(TipoItem.Articulo, "Heladera marca Hp", 1700.00);
        Proveedor juancito = new Proveedor("Juan Pablo Segundo", 20204523, "Calle falsa 123");
        DocumentoComercial factura = new DocumentoComercial(TipoDocumento.Factura, 420423042);
        compraTest = new OperacionDeEgreso();
        compraTest.adjuntar(factura);
        compraTest.agregarItem(heladera);
        compraTest.agregarItem(microondas);
        compraTest.setProveedor(juancito);

        presupuestoTest = new Presupuesto(new ArrayList<Item>(Arrays.asList(microondas2)),juancito );

        compraTest.agregarPresupuesto(presupuestoTest);

        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(compraTest);
        this.entityManager.getTransaction().commit();
        //Guardar la casa y ver que se genero una fila en la base de datos

    }

    @Test
    public void testPersistenciaIngresos() {
//        OperacionDeIngreso i1 = new OperacionDeIngreso("Operacion 1", 3500.0, LocalDate.parse("2018-02-27"));
        OperacionDeIngreso i1 = new OperacionDeIngreso("Operacion 1", 3500.0, LocalDate.now());
        i1.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));

        OperacionDeIngreso i2 = new OperacionDeIngreso("Operacion 2", 3500.0, LocalDate.parse("2018-02-27"));
        CriterioAceptacion ci2 = new CriterioAceptacion(TipoCriterio.FECHA_DESDE_HASTA);
        ci2.agregarParametros("fechaDesde", LocalDate.parse("2018-02-27"));
        ci2.agregarParametros("fechaHasta", LocalDate.parse("2020-02-27"));
        i2.agregarCriterio(ci2);

        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(i1);
        this.entityManager.persist(i2);
        this.entityManager.getTransaction().commit();
    }

    @Test
    public void testCargaIngresos() {
        this.entityManager = entityManagerFactory.createEntityManager();
        //Crear una casa con un cliente SQL
        OperacionDeIngreso o1 = this.entityManager.find(OperacionDeIngreso.class,1);
        OperacionDeIngreso o2 = this.entityManager.find(OperacionDeIngreso.class,2);

        //Imprimirla por pantalla
        System.out.println(o1.getDescripcion());
        System.out.println(o2.getDescripcion());
        System.out.println(o2.obtenerCriterioAceptacion(0).getParametro("fechaDesde"));
        System.out.println(o2.obtenerCriterioAceptacion(0).getParametro("fechaHasta"));
    }

}
