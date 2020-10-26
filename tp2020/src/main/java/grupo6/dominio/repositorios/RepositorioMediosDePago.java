package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.MedioDePago;
import grupo6.dominio.servicios.AdapterMediosDePago;
import grupo6.dominio.servicios.AdapterMediosDePagoMP;

import java.util.List;

public class RepositorioMediosDePago {
    private List<MedioDePago> medioDePagos;

    private static RepositorioMediosDePago yoMismo = null;

    public static RepositorioMediosDePago getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioMediosDePago(new AdapterMediosDePagoMP());
        }
        return yoMismo;
    }

    public RepositorioMediosDePago(AdapterMediosDePago adapterMediosDePago){
        this.medioDePagos = adapterMediosDePago.getMediosDePago();
    }

    public MedioDePago buscar(String id){
        return this.medioDePagos.stream().filter(e -> e.getId().equals(id)).findFirst().get();
    }

    public List<MedioDePago> getMedioDePagos() {
        return medioDePagos;
    }
}
