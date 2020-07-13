package grupo6.dominio;

import java.util.Objects;

public class Vinculacion {
    private int operacionIngresoID;
    private int operacionEgresoID;
    private Double montoVinculado;

    public Vinculacion(int ingreso, int egreso, double monto){
        operacionEgresoID = egreso;
        operacionIngresoID = ingreso;
        montoVinculado = monto;
    }

    public int getOperacionIngresoID() {
        return operacionIngresoID;
    }

    public int getOperacionEgresoID() {
        return operacionEgresoID;
    }

    public Double getMontoVinculado() {
        return montoVinculado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vinculacion that = (Vinculacion) o;
        return operacionIngresoID == that.operacionIngresoID &&
            operacionEgresoID == that.operacionEgresoID &&
            Objects.equals(montoVinculado, that.montoVinculado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operacionIngresoID, operacionEgresoID, montoVinculado);
    }
}

