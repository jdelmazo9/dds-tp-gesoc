package grupo6.dominio.entidades;

import java.util.ArrayList;

public class Actividad {
   String actividad;
   ArrayList<Integer> valoresMaximosCantPersonal;
   ArrayList<Double> valoresMaximosVentasTotales;

    public Actividad(String actividad, ArrayList<Integer> valoresMaximosCantPersonal, ArrayList<Double> valoresMaximosVentasTotales) {
        this.actividad = actividad;
        this.valoresMaximosCantPersonal = valoresMaximosCantPersonal;
        this.valoresMaximosVentasTotales = valoresMaximosVentasTotales;
    }
}
