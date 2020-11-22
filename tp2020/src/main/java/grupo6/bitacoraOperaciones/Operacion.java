package grupo6.bitacoraOperaciones;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Entity("Operaciones")
public class Operacion {
    @Id
    ObjectId id;

    String user;
    String entidad;
    TipoOperacion tipo_operacion;
    String description;
    LocalDate timestamp;

    public Operacion() {}

    public Operacion(String user, String entidad, String desc, TipoOperacion tipo){
        this.user = user;
        description = desc;
        this.entidad = entidad;
        timestamp = LocalDate.now();
        tipo_operacion = tipo;
    }
}
