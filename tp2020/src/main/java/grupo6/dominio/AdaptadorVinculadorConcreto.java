package grupo6.dominio;

import grupo6.vinculaciones.VinculadorExterno;

import java.util.ArrayList;

public class AdaptadorVinculadorConcreto implements AdaptadorVinculador {
    private VinculadorExterno vinculadorExterno;

    public AdaptadorVinculadorConcreto(){
        vinculadorExterno = new VinculadorExterno();
    }

    public ArrayList<Vinculacion> vincular(ArrayList<OperacionDeIngreso> ingresosOriginal, ArrayList<OperacionDeEgreso> egresosOriginal, CriteriosEnum criterio) {
        ArrayList<IngresoDTO> ingresos = prepararIngresos(ingresosOriginal);
        ArrayList<EgresoDTO> egresos = prepararEgresos(egresosOriginal);
        return vinculadorExterno.vincular(ingresos, egresos, criterio);
    }

    private ArrayList<EgresoDTO> prepararEgresos(ArrayList<OperacionDeEgreso> egresos) {
        ArrayList<EgresoDTO> egresosDTO = new ArrayList<>();
        for (OperacionDeEgreso e : egresos) {
            egresosDTO.add(new EgresoDTO(e.getId(), e.getFecha(), e.getValorTotal()));
        }
        return egresosDTO;
    }

    private ArrayList<IngresoDTO> prepararIngresos(ArrayList<OperacionDeIngreso> ingresos) {
        ArrayList<IngresoDTO> ingresosDTO = new ArrayList<>();
        for (OperacionDeIngreso i : ingresos) {
            ingresosDTO.add(new IngresoDTO(i.getId(), i.getFecha(), i.getMonto()));
        }
        return ingresosDTO;
    }
}
