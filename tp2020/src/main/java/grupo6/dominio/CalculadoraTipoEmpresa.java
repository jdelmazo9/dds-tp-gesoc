package grupo6.dominio;

public class CalculadoraTipoEmpresa {
    //private static AdaptadorClasificacionEmpresa servicio;
    static AdaptadorClasificacionEmpresa servicio = new TipoEmpresaHardcode();

    public static TipoEmpresa calcularTipoEmpresa (Integer cantidadPersonal, String actividadNombre, Double promedioVentasAnual) {
        Actividad actividad =  servicio.obtenerActividad(actividadNombre);
        TipoEmpresa tipoEmpresa = null;
        for(int i = 0; i < TipoEmpresa.values().length ; i++) {
            if(cantidadPersonal <= actividad.valoresMaximosCantPersonal.get(i)  || promedioVentasAnual <= actividad.valoresMaximosVentasTotales.get(i) ) {
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


