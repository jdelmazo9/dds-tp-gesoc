package grupo6.vinculaciones;

import java.time.LocalDate;

public class IngresoExt extends OperacionExt{
    public int id;
    public LocalDate fecha;
    public Double monto;
    public Double montoSinVincular;

    public IngresoExt(int id, LocalDate fecha, Double monto){
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.montoSinVincular = monto;
    }
}
