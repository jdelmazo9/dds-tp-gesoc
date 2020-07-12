package grupo6.vinculaciones;

public class Vinculacion {
    private int operacionIngresoID;
    private int operacionEgresoID;
    private Double montoVinculado;

    public Vinculacion(int ingreso, int egreso, double monto){
        operacionEgresoID = ingreso;
        operacionIngresoID = egreso;
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

}
