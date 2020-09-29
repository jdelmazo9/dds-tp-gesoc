package grupo6.vinculaciones;

import grupo6.dominio.entidades.Vinculacion;

import java.util.ArrayList;

public interface TipoVinculador {
    ArrayList<Vinculacion> vincular(java.util.ArrayList<IngresoExt> ingresos,
                                           ArrayList<EgresoExt> egresos);
}
