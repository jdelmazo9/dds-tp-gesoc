package grupo6.vinculaciones;

import grupo6.dominio.Vinculacion;

import java.util.ArrayList;

import static java.lang.Double.min;

public class VinculadorPrimeroIngreso implements TipoVinculador {
    public ArrayList<Vinculacion> vincular(ArrayList<IngresoExt> ingresos, ArrayList<EgresoExt> egresos) {
        ArrayList<Vinculacion> vinculaciones = new ArrayList<>();
        for (IngresoExt i: ingresos) {
            for (EgresoExt e: egresos){
                if(!i.acepta(e) || e.montoSinVincular == 0)
                    continue;
                double montoVinculado = min(i.montoSinVincular, e.montoSinVincular);
                vinculaciones.add(new Vinculacion(i.id, e.id, montoVinculado));
                i.montoSinVincular -= montoVinculado;
                e.montoSinVincular -= montoVinculado;
                if (i.montoSinVincular == 0)
                    break;
            }
        }
        return vinculaciones;
    }
}
