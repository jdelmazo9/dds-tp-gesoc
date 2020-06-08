package grupo6.dominio;

import java.util.ArrayList;

public class CalculadoraTipoEmpresa {
    //private static AdaptadorClasificacionEmpresa servicio;
    static AdaptadorClasificacionEmpresa servicio = new TipoEmpresaHardcode();

    public static TipoEmpresa calcularTipoEmpresa (Integer cantidadPersonal, String actividadNombre, Double promedioVentasAnual) {
        Actividad actividad =  servicio.obtenerActividad(actividadNombre);
        TipoEmpresa tipoEmpresa = null;
        for(int i = 0; i < TipoEmpresa.values().length ; i++) {
            if(cantidadPersonal <= actividad.valoresMinimosCantPersonal.get(i)  || promedioVentasAnual <= actividad.valoresMinimosVentasTotales.get(i) ) {
                tipoEmpresa = TipoEmpresa.values()[i];
                break;
            }
        }
        if(tipoEmpresa == null) {
            //ERROR - NO ES PYMES -> MANEJARLO
            return null;
        }
        return tipoEmpresa;
    }
}


