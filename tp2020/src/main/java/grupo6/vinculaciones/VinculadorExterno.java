package grupo6.vinculaciones;

import grupo6.dominio.CriteriosEnum;
import grupo6.dominio.EgresoDTO;
import grupo6.dominio.IngresoDTO;
import grupo6.dominio.Vinculacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import static java.lang.Double.min;

public class VinculadorExterno {
    public ArrayList<Vinculacion> vincular(ArrayList<IngresoDTO> ingresosDTO,
                                           ArrayList<EgresoDTO> egresosDTO,
                                           CriteriosEnum criterioOriginal) {

        ArrayList<IngresoExt> ingresos = convertirIngresos(ingresosDTO);
        ArrayList<EgresoExt> egresos = convertirEgresos(egresosDTO);
        Comparator<OperacionExt> comparador = interpretarCriterio(criterioOriginal);

        // Ordeno ingresos y egresos
        ingresos.sort(comparador);
        egresos.sort(comparador);

        Iterator<IngresoExt> ingresosIterator = ingresos.iterator();
        Iterator<EgresoExt> egresosIterator = egresos.iterator();

        ArrayList<Vinculacion> vinculaciones = new ArrayList<>();

        IngresoExt i = ingresosIterator.next();
        EgresoExt e = egresosIterator.next();

        // empezar a vincular desde ingresos
        while (true) {
            double montoVinculado = min(i.montoSinVincular, e.montoSinVincular);
            vinculaciones.add(new Vinculacion(i.id, e.id, montoVinculado));
            i.montoSinVincular -= montoVinculado;
            e.montoSinVincular -= montoVinculado;
            if (i.montoSinVincular == 0) {
                if (!ingresosIterator.hasNext())
                    break;
                i = ingresosIterator.next();
            }
            if (e.montoSinVincular == 0) {
                if (!egresosIterator.hasNext())
                    break;
                e = egresosIterator.next();
            }
        }
        return vinculaciones;
    }

    private ArrayList<EgresoExt> convertirEgresos(ArrayList<EgresoDTO> egresosDTO) {
        ArrayList<EgresoExt> egresos = new ArrayList<>();
        for (EgresoDTO o : egresosDTO) {
            egresos.add(new EgresoExt(o.id, o.fecha, o.monto));
        }
        return egresos;
    }

    private ArrayList<IngresoExt> convertirIngresos(ArrayList<IngresoDTO> ingresosDTO) {
        ArrayList<IngresoExt> ingresos = new ArrayList<>();
        for (IngresoDTO o : ingresosDTO) {
            ingresos.add(new IngresoExt(o.id, o.fecha, o.monto));
        }
        return ingresos;
    }

    private Comparator<OperacionExt> interpretarCriterio(CriteriosEnum criterioEnum){
        return TraductorCriterios.obtenerComparador(criterioEnum);
    }

}
