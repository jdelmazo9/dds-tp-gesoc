package grupo6.dominio;

import java.time.LocalDate;

public class IngresoDTO {
    public int id;
    public LocalDate fecha;
    public Double monto;

    public IngresoDTO(int id, LocalDate fecha, Double monto){
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
    }
}
