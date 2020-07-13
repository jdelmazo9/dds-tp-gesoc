package grupo6.dominio;

import java.util.ArrayList;

public interface AdaptadorVinculador {
    public ArrayList<Vinculacion> vincular(ArrayList<OperacionDeIngreso> ingresosOriginal,
                                           ArrayList<OperacionDeEgreso> egresosOriginal,
                                           CriteriosEnum criterio);
}
