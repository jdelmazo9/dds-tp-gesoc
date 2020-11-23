package grupo6.bitacoraOperaciones;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.bson.Document;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServicioRegistroOperaciones {
    Datastore datastore;
    static MongoClient mongoClient;
    static ServicioRegistroOperaciones instancia = null;

    public static ServicioRegistroOperaciones getInstancia() {
        if(instancia == null){
            instancia = new ServicioRegistroOperaciones();
        }
        return instancia;
    }

    private ServicioRegistroOperaciones(){
        mongoClient = MongoClients.create(
            "mongodb+srv://admin:Y7vxjFD6TCG7v4V@dds-gesoc.njuxr.mongodb.net/admin?retryWrites=true&w=majority"
        );
        datastore = Morphia.createDatastore(mongoClient, "bitacora_operaciones");
    }

    public void registrarOperacion(String user, String entidad, TipoOperacion tipoOperacion, Integer entidad_id){
        String desc = tipoOperacion.name() + " sobre " + entidad + " de id " + entidad_id + " realizada por " + user;
        datastore.save(new Operacion(user, entidad, tipoOperacion, entidad_id, desc));
    }

    public String obtenerOperaciones() {
        return StreamSupport.stream(datastore.getDatabase().getCollection("Operaciones").find().spliterator(), false)
            .map(Document::toJson)
            .collect(Collectors.joining(", ", "[", "]"));
    }

    public String obtenerOperaciones(TipoOperacion tipo) {
        Document query = new Document();
        query.append("tipo_operacion", tipo);
        return StreamSupport.stream(datastore.getDatabase().getCollection("Operaciones").find(query).spliterator(), false)
            .map(Document::toJson)
            .collect(Collectors.joining(", ", "[", "]"));
    }

    public String obtenerOperaciones(String entidad, TipoOperacion tipo) {
        Document query = new Document();
        query.append("tipo_operacion", tipo);
        query.append("entidad", entidad);
        return StreamSupport.stream(datastore.getDatabase().getCollection("Operaciones").find(query).spliterator(), false)
            .map(Document::toJson)
            .collect(Collectors.joining(", ", "[", "]"));
    }

    public String obtenerOperaciones(String entidad) {
        Document query = new Document();
        query.append("entidad", entidad);
        return StreamSupport.stream(datastore.getDatabase().getCollection("Operaciones").find(query).spliterator(), false)
            .map(Document::toJson)
            .collect(Collectors.joining(", ", "[", "]"));
    }

}
