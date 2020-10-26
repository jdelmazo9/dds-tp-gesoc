package grupo6.dominio.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "documentoItemTipo", discriminatorType = DiscriminatorType.STRING)
public abstract class DocumentoItems {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }
}
