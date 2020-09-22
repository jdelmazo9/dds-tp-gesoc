package grupo6.vinculaciones;

import grupo6.dominio.entidades.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class VinculadorExterno {
    public ArrayList<Vinculacion> vincular(ArrayList<IngresoDTO> ingresosDTO,
                                           ArrayList<EgresoDTO> egresosDTO,
                                           CriteriosEnum criterioOriginal) {

        ArrayList<IngresoExt> ingresos = convertirIngresos(ingresosDTO);
        ArrayList<EgresoExt> egresos = convertirEgresos(egresosDTO);
        Comparator<OperacionExt> comparador = interpretarCriterioComparador(criterioOriginal);
        TipoVinculador vinculador = interpretarCriterioVinculador(criterioOriginal);

        // Ordeno ingresos y egresos
        ingresos.sort(comparador);
        egresos.sort(comparador);

        ArrayList<Vinculacion> vinculaciones = vinculador.vincular(ingresos, egresos);

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
            ingresos.add(new IngresoExt(o.id, o.fecha, o.monto, convertirCriterios(o.criterios)));
        }
        return ingresos;
    }

    private ArrayList<CriterioAceptacionEgresos> convertirCriterios(ArrayList<CriterioAceptacion> criterios){
        ArrayList <CriterioAceptacionEgresos> criteriosConv = new ArrayList<>();
        for (CriterioAceptacion c: criterios ) {
            switch (c.getTipoCriterio()){
                case FECHA_DESDE_HASTA:
                    criteriosConv.add(
                        new CriterioFechaDesdeHasta(
                            (LocalDate) c.getParametro("fechaDesde"),
                            (LocalDate) c.getParametro("fechaHasta")));
                    break;
                case SIN_RESTRICCION:
                    criteriosConv.add(new CriterioSinRestriccion());
                    break;
            }
        }
        return criteriosConv;
    }

    private Comparator<OperacionExt> interpretarCriterioComparador(CriteriosEnum criterioEnum){
        return TraductorCriterios.obtenerComparador(criterioEnum);
    }

    private TipoVinculador interpretarCriterioVinculador(CriteriosEnum criterioEnum){
        return TraductorCriterios.obtenerTipoVinculador(criterioEnum);
    }
}
