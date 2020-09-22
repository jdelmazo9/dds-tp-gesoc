package grupo6.dominio.entidades;

import java.util.ArrayList;
import java.time.LocalDate;

public class OperacionDeIngreso {
    private ArrayList<Categoria> categorias;
    private String desc;
    private Double monto;
    private int id;
    private static int cantidadIngresos = 0;
    private LocalDate fecha;
    private ArrayList<CriterioAceptacion> criterios;

    public OperacionDeIngreso(String desc, Double monto){
        this.id = ++cantidadIngresos;
        this.desc = desc;
        this.monto = monto;
        fecha = LocalDate.now();
        categorias = new ArrayList();
        criterios = new ArrayList();
    }

    public OperacionDeIngreso(String desc, Double monto, LocalDate fecha){
        this.id = ++cantidadIngresos;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void agregarCriterio(CriterioAceptacion criterio) {
        this.criterios.add(criterio);
    }

    public ArrayList<CriterioAceptacion> getCriterios() {
        return criterios;
    }

    public void setearCriterioFechaDesdeHasta(LocalDate desde, LocalDate hasta){
        CriterioAceptacion criterio = new CriterioAceptacion(TipoCriterio.FECHA_DESDE_HASTA);
        criterio.agregarParametros("fechaDesde", desde);
        criterio.agregarParametros("fechaHasta", hasta);
        criterios.add(criterio);
    }
}
