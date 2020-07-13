package grupo6.dominio;

import java.util.ArrayList;
import java.time.LocalDate;

public class OperacionDeIngreso {
    private ArrayList<Categoria> categorias;
    private String desc;
    private Double monto;
    private int id;
    private static int cantidadIngresos = 0;
    private LocalDate fecha;

    public OperacionDeIngreso(String desc, Double monto){
        this.id = ++cantidadIngresos;
        this.desc = desc;
        this.monto = monto;
        fecha = LocalDate.now();
    }

    public OperacionDeIngreso(String desc, Double monto, LocalDate fecha){
        this.id = ++cantidadIngresos;
        this.desc = desc;
        this.monto = monto;
        this.fecha = fecha;
    }

    public void agregarCategoria(Categoria categoria) { this.categorias.add(categoria); }

    public int getId() {
        return id;
    }

    public Double getMonto() {
        return monto;
    }

    public String getDesc() {
        return desc;
    }

    public LocalDate getFecha() {
        return fecha;
    }

}
