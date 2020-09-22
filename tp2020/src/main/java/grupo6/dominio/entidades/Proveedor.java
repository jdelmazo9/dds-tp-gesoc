package grupo6.dominio.entidades;

public class Proveedor {
    private String nombre;
    private int nroIdentificacion;
    private String direccionPostal;

    public Proveedor(String nombre, int nroIdentificacion, String direccionPostal) {
        this.nombre = nombre;
        this.nroIdentificacion = nroIdentificacion;
        this.direccionPostal = direccionPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroIdentificacion() {
        return nroIdentificacion;
    }

    public void setNroIdentificacion(int nroIdentificacion) {
        this.nroIdentificacion = nroIdentificacion;
    }

    public String getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(String direccionPostal) {
        this.direccionPostal = direccionPostal;
    }
}
