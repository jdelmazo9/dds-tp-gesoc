package grupo6.dominio.repositorios.daos;

import grupo6.dominio.entidades.OperacionDeEgreso;
import grupo6.dominio.entidades.OperacionDeIngreso;
import grupo6.dominio.entidades.Presupuesto;

import java.util.ArrayList;
import java.util.List;

public class OperacionDTO {
    private List<OperacionDeIngreso> ingresos ;
    private ArrayList<Presupuesto> presupuestos ;

    public List<OperacionDeIngreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<OperacionDeIngreso> ingresos) {
        this.ingresos = ingresos;
    }

    public ArrayList<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

    public void setPresupuestos(ArrayList<Presupuesto> presupuestos) {
        this.presupuestos = presupuestos;
    }
}
