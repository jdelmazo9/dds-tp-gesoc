package grupo6.dominio.entidades;

import java.time.LocalDate;

public class EgresoDTO {
    public int id;
    public LocalDate fecha;
    public Double monto;

    public EgresoDTO(int id, LocalDate fecha, Double monto){
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
    }
}
