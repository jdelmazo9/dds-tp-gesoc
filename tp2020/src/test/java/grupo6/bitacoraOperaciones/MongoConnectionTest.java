package grupo6.bitacoraOperaciones;

import org.junit.jupiter.api.Test;

import static grupo6.bitacoraOperaciones.TipoOperacion.valueOfLabel;
import static org.junit.Assert.assertEquals;

public class MongoConnectionTest {

//    Datastore datastore;
//    static MongoClient mongoClient;

//    @BeforeEach
//    void setUp() {
//        mongoClient = MongoClients.create(
//            "mongodb+srv://admin:Y7vxjFD6TCG7v4V@dds-gesoc.njuxr.mongodb.net/admin?retryWrites=true&w=majority"
//        );
//        datastore = Morphia.createDatastore(mongoClient, "bitacora_operaciones");
//        datastore.getMapper().mapPackage("grupo6.bitacoraOperaciones");
//        datastore.ensureIndexes();
//    }
//
    @Test
    void persistenceTest(){
        ServicioRegistroOperaciones.getInstancia().registrarOperacion("mzuc","ingresos", TipoOperacion.CREATE, 1);
//        ServicioRegistroOperaciones.getInstancia().registrarOperacion("mzuc","ingresos", "3", TipoOperacion.DELETE);
    }

    @Test
    void obtenerTodosTodos(){
        System.out.println(ServicioRegistroOperaciones.getInstancia().obtenerOperaciones());
    }

    @Test
    void obtenerTodosLosCreate(){
        System.out.println(ServicioRegistroOperaciones.getInstancia().obtenerOperaciones(TipoOperacion.CREATE));
    }

    @Test
    void obtenerTodosLosIngresos(){
        System.out.println(ServicioRegistroOperaciones.getInstancia().obtenerOperaciones("ingresos"));
    }

    @Test
    void obtenerTodosLosEgresosCreados(){
        System.out.println(ServicioRegistroOperaciones.getInstancia().obtenerOperaciones("ingresos", TipoOperacion.CREATE));
    }

    @Test
    void enummmmm(){
        assertEquals(TipoOperacion.CREATE, TipoOperacion.valueOfLabel("CREATE"));
    }

//    @AfterAll
//    static void closeConnection(){
//        mongoClient.close();
//    }

//    @Test
//    void readCollection(){
//        Block<Document> printBlock = document -> System.out.println(document.toJson());
//        MongoDatabase mongoDB = mongoClient.getDatabase("sample_airbnb");
//        MongoCollection<Document> collection = mongoDB.getCollection("listingsAndReviews");
//        FindIterable<Document> findIterable = collection.find(new Document());
//        System.out.println(findIterable.first().toJson());
//    }

//    @Test
//    void databasesTest(){
//        MongoIterable<String> databases = mongoClient.listDatabaseNames();
//        for (String db: databases) {
//            System.out.println(db);
//        }
//    }
}

