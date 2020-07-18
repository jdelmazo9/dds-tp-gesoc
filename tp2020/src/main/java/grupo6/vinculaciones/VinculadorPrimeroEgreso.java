package grupo6.vinculaciones;

import grupo6.dominio.Vinculacion;

import java.util.ArrayList;

import static java.lang.Double.min;

public class VinculadorPrimeroEgreso implements TipoVinculador {
    public ArrayList<Vinculacion> vincular(ArrayList<IngresoExt> ingresos, ArrayList<EgresoExt> egresos) {
        ArrayList<Vinculacion> vinculaciones = new ArrayList<>();
        for (EgresoExt e: egresos){
            for (IngresoExt i: ingresos) {
                if(!i.acepta(e) || i.montoSinVincular == 0)
                    continue;
                double montoVinculado = min(i.montoSinVincular, e.montoSinVincular);
                vinculaciones.add(new Vinculacion(i.id, e.id, montoVinculado));
                i.montoSinVincular -= montoVinculado;
                e.montoSinVincular -= montoVinculado;
                if (e.montoSinVincular == 0)
                    break;
            }
        }
        return vinculaciones;
    }
}
