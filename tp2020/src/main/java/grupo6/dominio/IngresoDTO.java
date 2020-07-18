package grupo6.dominio;

import java.time.LocalDate;
import java.util.ArrayList;

public class IngresoDTO {
    public int id;
    public LocalDate fecha;
    public Double monto;
    public ArrayList<CriterioAceptacion> criterios;

    public IngresoDTO(int id, LocalDate fecha, Double monto, ArrayList<CriterioAceptacion> criterios){
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.criterios = criterios;
    }
}
