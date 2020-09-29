package grupo6.vinculaciones;

import grupo6.dominio.entidades.CriteriosEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public final class TraductorCriterios {
    private static HashMap<CriteriosEnum, ComparadorOperaciones> criterioComparadorMap;
    private static HashMap<CriteriosEnum, TipoVinculador> tipoVinculadorMap;

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

        tipoVinculadorMap = new HashMap<>();
        tipoVinculadorMap.put(CriteriosEnum.CRITERIO_VALOR_PRIMERO_EGRESO, new VinculadorPrimeroEgreso());
        tipoVinculadorMap.put(CriteriosEnum.CRITERIO_VALOR_PRIMERO_INGRESO, new VinculadorPrimeroIngreso());
        tipoVinculadorMap.put(CriteriosEnum.CRITERIO_FECHA, new VinculadorPrimeroEgreso());
        tipoVinculadorMap.put(CriteriosEnum.CRITERIO_MIX_FECHA_VALOR, new VinculadorPrimeroEgreso());
        tipoVinculadorMap.put(CriteriosEnum.CRITERIO_MIX_VALOR_FECHA, new VinculadorPrimeroEgreso());
    }

    public static final ComparadorOperaciones obtenerComparador(CriteriosEnum criterio){
        return criterioComparadorMap.get(criterio);
    }

    public static final TipoVinculador obtenerTipoVinculador(CriteriosEnum criterio){
        return tipoVinculadorMap.get(criterio);
    }
}
