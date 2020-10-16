package grupo6.dominio.entidades;

public class MedioDePago {
    private TipoPago medio;
    private String id;
    private String nombre;
    private String imagen;

    public MedioDePago(TipoPago medio, String id, String nombre, String imagen) {
        this.medio = medio;
        this.id = id;
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
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
