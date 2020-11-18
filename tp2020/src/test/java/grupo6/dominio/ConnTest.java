//package grupo6.dominio;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import grupo6.seguridad.RolUsuario;
//import grupo6.seguridad.Usuario;
//
//import java.sql.*;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class ConnTest {
//
//    private static EntityManagerFactory entityManagerFactory;
//
//    @BeforeClass
//    public static void setUpClass() {
//        entityManagerFactory = Persistence.createEntityManagerFactory("db");
//    }
//
//    @Test
//    public void testConn() throws Exception {
//        final String DB_CONNECTION = "jdbc:mysql://localhost:3306/dds";
//        final String DB_USER = "root";
//        final String DB_PASSWORD = "100296";
//        Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,	DB_PASSWORD);
//
//        Statement statement = dbConnection.createStatement();
//        ResultSet rs = statement.executeQuery("SELECT PersonID, LastName from Persons");
//        while (rs.next()) {
//            Integer id = rs.getInt("PersonID");
//            String prop = rs.getString("LastName");
//            System.out.println(id);
//            System.out.println(prop);
//        }
//
//    }
//
//    @Test
//    public void creaTablaUsuario(){
//
//        EntityManager entityManager;
//
//        entityManager = entityManagerFactory.createEntityManager();
//
//        entityManager.getTransaction().begin();
//
//        Usuario yo = new Usuario("yo", "ashgfajf093", RolUsuario.ADMIN);
//
//        entityManager.persist(yo);
//        entityManager.getTransaction().commit();
//
//    }
//}
