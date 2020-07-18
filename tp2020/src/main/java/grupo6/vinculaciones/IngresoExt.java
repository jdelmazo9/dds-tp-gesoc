package grupo6.vinculaciones;

import java.time.LocalDate;
import java.util.ArrayList;

public class IngresoExt extends OperacionExt{
    public ArrayList<CriterioAceptacionEgresos> criterios;

    public IngresoExt(int id, LocalDate fecha, Double monto, ArrayList<CriterioAceptacionEgresos> criterios){
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.montoSinVincular = monto;
        this.criterios = criterios;
    }

    public boolean acepta(EgresoExt egreso){
        return criterios.stream().allMatch(c->c.acepta(egreso));
    }
}
