package grupo6.dominio.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IngresoDTO {
    public int id;
    public LocalDate fecha;
    public Double monto;
    public List<CriterioAceptacion> criterios;

    public IngresoDTO(int id, LocalDate fecha, Double monto, List<CriterioAceptacion> criterios){
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.criterios = criterios;
    }
}
