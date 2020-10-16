package grupo6.dominio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import grupo6.seguridad.RolUsuario;
import grupo6.seguridad.Usuario;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class PersistenceTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager = null;
    private Usuario usuario = new Usuario("Cosme", "1234", RolUsuario.ADMIN);

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
        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(usuario);
        this.entityManager.getTransaction().commit();
        //Guardar la casa y ver que se genero una fila en la base de datos

    }

}
