package grupo6.dominio.entidades;

public class EntidadBase {
    private String nombreFicticio;
    private String descripcion;
    public EntidadBase(String nombreFicticio) {
        this.nombreFicticio = nombreFicticio;
    }
    public EntidadBase(String nombreFicticio, String descripcion) {
        this.nombreFicticio = nombreFicticio;
        this.descripcion = descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getNombreFicticio() {
        return this.nombreFicticio;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
}
