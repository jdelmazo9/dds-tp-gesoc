package grupo6.dominio;

import java.math.BigInteger;
import java.util.ArrayList;

public class Actividad {
   String actividad;
   ArrayList<Integer> valoresMinimosCantPersonal;
   ArrayList<Double> valoresMinimosVentasTotales;

    public Actividad(String actividad, ArrayList<Integer> valoresMinimosCantPersonal, ArrayList<Double> valoresMinimosVentasTotales) {
        this.actividad = actividad;
        this.valoresMinimosCantPersonal = valoresMinimosCantPersonal;
        this.valoresMinimosVentasTotales = valoresMinimosVentasTotales;
    }
}
