package grupo6.bitacoraOperaciones;

import com.mongodb.MongoClient;
//import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Ejemplo {

    public static void main(String[] args) {
        MongoClientURI uri = new MongoClientURI("mongodb+srv://admin:Y7vxjFD6TCG7v4V@dds-gesoc.njuxr.mongodb.net/admin?retryWrites=true&w=majority");
        MongoClient cliente = new MongoClient(uri);
//        MongoClient mongoClient = MongoClients.create(
//            "mongodb+srv://admin:Y7vxjFD6TCG7v4V@dds-gesoc.njuxr.mongodb.net/admin?retryWrites=true&w=majority");
        MongoDatabase database = cliente.getDatabase("bitacora_operaciones");
        System.out.println(database.getCollection("Operaciones").countDocuments());
    }
}
