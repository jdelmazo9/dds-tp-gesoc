package grupo6.dominio.entidades;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@Entity
public class OperacionDeIngreso {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @ManyToMany
    private List<Categoria> categorias;
    private String desc;
    private Double monto;
    private LocalDate fecha;
    @Transient
    private List<CriterioAceptacion> criterios;

    public OperacionDeIngreso(String desc, Double monto){
        this.desc = desc;
        this.monto = monto;
        fecha = LocalDate.now();
        categorias = new ArrayList();
        criterios = new ArrayList();
    }

    public OperacionDeIngreso(String desc, Double monto, LocalDate fecha){
        this.desc = desc;
        this.monto = monto;
        this.fecha = fecha;
        categorias = new ArrayList();
        criterios = new ArrayList();
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

    public void setFechaStr(String fecha){
        System.out.println(fecha);
        this.setFecha(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println(this.fecha);
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getFechaStr() {
        return fecha.toString();
    }

    public void agregarCriterio(CriterioAceptacion criterio) {
        this.criterios.add(criterio);
    }

    public ArrayList<CriterioAceptacion> getCriterios() {
        return (ArrayList<CriterioAceptacion>) criterios;
    }

    public void setearCriterioFechaDesdeHasta(LocalDate desde, LocalDate hasta){
        CriterioAceptacion criterio = new CriterioAceptacion(TipoCriterio.FECHA_DESDE_HASTA);
        criterio.agregarParametros("fechaDesde", desde);
        criterio.agregarParametros("fechaHasta", hasta);
        criterios.add(criterio);
    }
}
