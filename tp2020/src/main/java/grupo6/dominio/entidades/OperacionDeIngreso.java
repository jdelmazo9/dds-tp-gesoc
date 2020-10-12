package grupo6.dominio.entidades;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class OperacionDeIngreso {
    private ArrayList<Categoria> categorias;
    private String desc;
    private Double monto;
    private int id;
    private static int cantidadIngresos = 0;
    private LocalDate fecha;
    private String fechaStr;
    private ArrayList<CriterioAceptacion> criterios;

    public OperacionDeIngreso(String desc, Double monto){
        this.id = ++cantidadIngresos;
        this.desc = desc;
        this.monto = monto;
        this.setFecha(LocalDate.now());
        categorias = new ArrayList();
        criterios = new ArrayList();
    }

    public OperacionDeIngreso(String desc, Double monto, LocalDate fecha){
        this.id = ++cantidadIngresos;
        this.desc = desc;
        this.monto = monto;
        this.setFecha(fecha);
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


    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
        this.fechaStr = fecha.toString();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getFechaStr() {
        return this.fechaStr;
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
