package grupo6.dominio;

import grupo6.vinculaciones.Vinculacion;

import java.util.ArrayList;

public interface AdaptadorVinculador {
    public ArrayList<Vinculacion> vincular(ArrayList<OperacionDeIngreso> ingresosOriginal,
                                           ArrayList<OperacionDeEgreso> egresosOriginal,
                                           CriteriosEnum criterio);
}
