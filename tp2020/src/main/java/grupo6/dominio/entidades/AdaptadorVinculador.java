package grupo6.dominio.entidades;

import java.util.ArrayList;

public interface AdaptadorVinculador {
    public ArrayList<Vinculacion> vincular(ArrayList<OperacionDeIngreso> ingresosOriginal,
                                           ArrayList<OperacionDeEgreso> egresosOriginal,
                                           CriteriosEnum criterio);
}
