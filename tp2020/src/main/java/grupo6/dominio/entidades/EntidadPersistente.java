package grupo6.dominio.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EntidadPersistente {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
