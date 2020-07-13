package grupo6.dominio;

import grupo6.vinculaciones.VinculadorExterno;

import java.util.ArrayList;

public class AdaptadorVinculadorConcreto implements AdaptadorVinculador {
    private VinculadorExterno vinculadorExterno;

    public AdaptadorVinculadorConcreto(){
        vinculadorExterno = new VinculadorExterno();
    }

    public ArrayList<Vinculacion> vincular(ArrayList<OperacionDeIngreso> ingresosOriginal, ArrayList<OperacionDeEgreso> egresosOriginal, CriteriosEnum criterio) {
        ArrayList<OperacionDTO> ingresos = prepararIngresos(ingresosOriginal);
        ArrayList<OperacionDTO> egresos = prepararEgresos(egresosOriginal);
        return vinculadorExterno.vincular(ingresos, egresos, criterio);
    }

    private ArrayList<OperacionDTO> prepararEgresos(ArrayList<OperacionDeEgreso> egresos) {
        ArrayList<OperacionDTO> egresosDTO = new ArrayList<>();
        for (OperacionDeEgreso e : egresos) {
            egresosDTO.add(new OperacionDTO(e.getId(), e.getFecha(), e.getValorTotal()));
        }
        return egresosDTO;
    }

    private ArrayList<OperacionDTO> prepararIngresos(ArrayList<OperacionDeIngreso> ingresos) {
        ArrayList<OperacionDTO> ingresosDTO = new ArrayList<>();
        for (OperacionDeIngreso i : ingresos) {
            ingresosDTO.add(new OperacionDTO(i.getId(), i.getFecha(), i.getMonto()));
        }
        return ingresosDTO;
    }
}
