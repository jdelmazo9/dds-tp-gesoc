package grupo6.dominio;

import java.util.ArrayList;
import java.util.Date;

public class OperacionDeIngreso {
    private ArrayList<Categoria> categorias;
    private int montoTotal;
    private String descripcion;
    private Date fecha;

    public OperacionDeIngreso(int montoTotal, String descripcion) {
        this.montoTotal = montoTotal;
        this.descripcion = descripcion;
        fecha = new Date();
        fecha.getTime();
    }
    public void agregarCategoria(Categoria categoria) { this.categorias.add(categoria); }
}
