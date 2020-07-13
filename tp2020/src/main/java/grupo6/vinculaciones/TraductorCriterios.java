package grupo6.vinculaciones;

import grupo6.dominio.CriteriosEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public final class TraductorCriterios {
    private static HashMap<CriteriosEnum, ComparadorOperaciones> criterioComparadorMap;

    static {
        criterioComparadorMap = new HashMap<>();
        criterioComparadorMap.put(CriteriosEnum.CRITERIO_VALOR_PRIMERO_EGRESO, new ComparadorValor());
        criterioComparadorMap.put(CriteriosEnum.CRITERIO_VALOR_PRIMERO_INGRESO, new ComparadorValor());
        criterioComparadorMap.put(CriteriosEnum.CRITERIO_FECHA, new ComparadorFecha());
        criterioComparadorMap.put(CriteriosEnum.CRITERIO_MIX_FECHA_VALOR, new ComparadorMix(
            new ArrayList<>(Arrays.asList(new ComparadorFecha(), new ComparadorValor()))
        ));
        criterioComparadorMap.put(CriteriosEnum.CRITERIO_MIX_VALOR_FECHA, new ComparadorMix(
            new ArrayList<>(Arrays.asList(new ComparadorValor(),new ComparadorFecha()))
        ));
    }

    public static final ComparadorOperaciones obtenerComparador(CriteriosEnum criterio){
        return criterioComparadorMap.get(criterio);
    }
}
