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

//    @ManyToMany(cascade = CascadeType.ALL)
    @ManyToMany
    private List<Categoria> categorias;
    private String descripcion;

    @Column(precision=10, scale=2)
    private Double monto;

    @Column
    private LocalDate fecha;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="ingresoID")
    private List<CriterioAceptacion> criterios;

    public OperacionDeIngreso(){}

    public OperacionDeIngreso(String desc, Double monto){
        this.descripcion = desc;
        this.monto = monto;
        fecha = LocalDate.now();
        categorias = new ArrayList();
        criterios = new ArrayList();
    }

    public OperacionDeIngreso(String desc, Double monto, LocalDate fecha){
        this.descripcion = desc;
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

    public String getDescripcion() {
        return descripcion;
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

    public List<CriterioAceptacion> getCriterios() {
        return criterios;
    }

    public CriterioAceptacion obtenerCriterioAceptacion(int index){
        return criterios.get(index);
    }

    public void setearCriterioFechaDesdeHasta(LocalDate desde, LocalDate hasta){
        CriterioAceptacion criterio = new CriterioAceptacion(TipoCriterio.FECHA_DESDE_HASTA);
        criterio.agregarParametros("fechaDesde", desde);
        criterio.agregarParametros("fechaHasta", hasta);
        criterios.add(criterio);
    }

    public boolean esDeCategorias(List<String> criterios, List<String> categorias){

        for(int i = 0; i < categorias.size(); i++) {
            Boolean pertenece = false;
            for(Categoria c : this.categorias) {
                if(c.getNombre().equals(categorias.get(i)) && c.getCriterio().getNombre().equals(criterios.get(i))) {
                    pertenece = true;
                }
            }
            if(!pertenece) {
                return false;
            }
        }

        return true;
    }
}
