package grupo6.vinculaciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import static java.lang.Double.min;

public class CriterioPrimeroIngreso extends CriterioOrdSimple {
    public void ordenar(ArrayList<OperacionDTO> ingresos, ArrayList<OperacionDTO> egresos) {
        // ordenar las 2 listas x valor
        ingresos.sort(new OrdOperacionValor());
        egresos.sort(new OrdOperacionValor());

        Iterator<OperacionDTO> ingresosIterator = ingresos.iterator();
        Iterator<OperacionDTO> egresosIterator = egresos.iterator();

        ArrayList<Vinculacion> vinculaciones = new ArrayList<>();

        OperacionDTO i = ingresosIterator.next();
        OperacionDTO e = egresosIterator.next();

        // empezar a vincular desde ingresos
        while (ingresosIterator.hasNext() && egresosIterator.hasNext()) {
            double montoVinculado = min(i.monto, e.monto);
            vinculaciones.add(new Vinculacion(i.id, e.id, montoVinculado));
            i.montoSinVincular -= montoVinculado;
            e.montoSinVincular -= montoVinculado;
            if(i.montoSinVincular == 0){
                i = ingresosIterator.next();
            }
            if(e.montoSinVincular == 0){
                e = egresosIterator.next();
            }
        }
    }
}
