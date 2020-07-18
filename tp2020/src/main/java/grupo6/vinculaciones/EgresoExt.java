package grupo6.vinculaciones;

import java.time.LocalDate;

public class EgresoExt extends OperacionExt{

    public EgresoExt(int id, LocalDate fecha, Double monto){
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.montoSinVincular = monto;
    }
}
