package grupo6.vinculaciones;

import grupo6.dominio.CriteriosEnum;
import grupo6.dominio.OperacionDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import static java.lang.Double.min;

public class VinculadorExterno {
    public ArrayList<Vinculacion> vincular(ArrayList<OperacionDTO> ingresosDTO,
                                           ArrayList<OperacionDTO> egresosDTO,
                                           CriteriosEnum criterioOriginal) {

        ArrayList<OperacionExt> ingresos = convertirOperaciones(ingresosDTO);
        ArrayList<OperacionExt> egresos = convertirOperaciones(egresosDTO);
        Comparator<OperacionExt> comparador = interpretarCriterio(criterioOriginal);

        // Ordeno ingresos y egresos
        ingresos.sort(comparador);
        egresos.sort(comparador);

        Iterator<OperacionExt> ingresosIterator = ingresos.iterator();
        Iterator<OperacionExt> egresosIterator = egresos.iterator();

        ArrayList<Vinculacion> vinculaciones = new ArrayList<>();

        OperacionExt i = ingresosIterator.next();
        OperacionExt e = egresosIterator.next();

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

    private ArrayList<OperacionExt> convertirOperaciones(ArrayList<OperacionDTO> operacionesDTO) {
        ArrayList<OperacionExt> operaciones = new ArrayList<>();
        for (OperacionDTO o : operacionesDTO) {
            operaciones.add(new OperacionExt(o.id, o.fecha, o.monto));
        }
        return operaciones;
    }

    private Comparator<OperacionExt> interpretarCriterio(CriteriosEnum criterioEnum){
        return TraductorCriterios.obtenerComparador(criterioEnum);
    }

}
