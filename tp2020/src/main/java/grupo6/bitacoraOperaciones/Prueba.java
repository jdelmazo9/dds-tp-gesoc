package grupo6.bitacoraOperaciones;// Requires official Java MongoDB Driver 2.14+



//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class Prueba {

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create(
            "mongodb+srv://admin:Y7vxjFD6TCG7v4V@dds-gesoc.njuxr.mongodb.net/admin?retryWrites=true&w=majority"
        );
        Datastore datastore = Morphia.createDatastore(mongoClient,"bitacora_operaciones");
        datastore.ensureIndexes();
        System.out.println(datastore.getDatabase().getCollection("Operaciones").countDocuments());

    }

}
