package grupo6.dominio.servicios;

import grupo6.dominio.entidades.MedioDePago;

import java.util.List;

public class AdapterMediosDePagoMP implements AdapterMediosDePago{

    @Override
    public List<MedioDePagoMP> getMediosDePago() {
        return ServicioMercadoPago.instancia().listadoMediosDePago();
    }
}
