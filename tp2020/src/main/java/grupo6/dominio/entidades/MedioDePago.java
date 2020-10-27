package grupo6.dominio.entidades;

import javax.persistence.*;

@Entity
public class MedioDePago {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private TipoPago medio;
    private String nombreID;
    private String nombre;
    private String imagen;

    public MedioDePago() {}

    public MedioDePago(TipoPago medio, String id, String nombre, String imagen) {
        this.medio = medio;
        this.nombreID = id;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public TipoPago getMedio() {
        return medio;
    }

    public void setMedio(TipoPago medio) {
        this.medio = medio;
    }

    public String getId() {
        return nombreID;
    }

    public void setId(String id) {
        this.nombreID = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
