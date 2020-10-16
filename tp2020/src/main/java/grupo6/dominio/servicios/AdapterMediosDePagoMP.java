package grupo6.dominio.servicios;

import grupo6.dominio.entidades.MedioDePago;
import grupo6.dominio.entidades.TipoPago;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterMediosDePagoMP implements AdapterMediosDePago{

    @Override
    public List<MedioDePago> getMediosDePago() {
        List<MedioDePagoMP> mediosDePagoMP = null;
        try {
            mediosDePagoMP = ServicioMercadoPago.instancia().listadoMediosDePago();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mediosDePagoMP.
                    stream().
                    map( mp -> new MedioDePago(TipoPago.valueOf(mp.payment_type_id), mp.id, mp.name, mp.secure_thumbnail)).
                    collect(Collectors.toList());
    }
}
