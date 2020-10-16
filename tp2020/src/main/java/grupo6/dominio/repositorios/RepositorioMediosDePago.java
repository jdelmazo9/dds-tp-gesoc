package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.MedioDePago;
import grupo6.dominio.servicios.AdapterMediosDePago;
import grupo6.dominio.servicios.MedioDePagoMP;

import java.util.List;

public class RepositorioMediosDePago {
    private List<MedioDePagoMP> medioDePagos;

    public RepositorioMediosDePago(AdapterMediosDePago adapterMediosDePago){
        medioDePagos = adapterMediosDePago.getMediosDePago();
    }
}
