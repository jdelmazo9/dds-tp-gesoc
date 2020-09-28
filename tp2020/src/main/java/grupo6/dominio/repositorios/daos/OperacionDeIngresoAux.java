package grupo6.dominio.repositorios.daos;

import grupo6.dominio.entidades.OperacionDeIngreso;

import java.util.List;

public class OperacionDeIngresoAux {
    private List<OperacionDeIngreso> ingresos ;

    public List<OperacionDeIngreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<OperacionDeIngreso> ingresos) {
        this.ingresos = ingresos;
    }
}
