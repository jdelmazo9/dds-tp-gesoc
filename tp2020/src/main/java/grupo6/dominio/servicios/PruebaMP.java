package grupo6.dominio.servicios;

import grupo6.dominio.entidades.MedioDePago;

import java.io.IOException;
import java.util.List;

public class PruebaMP {
    public static void main(String[] args) throws IOException {
        List<MedioDePagoMP> mp = ServicioMercadoPago.instancia().listadoMediosDePago();

        for (MedioDePagoMP m: mp) {

            System.out.println("Medio de pago: ");
            System.out.println(m.id);
            System.out.println(m.name);
            System.out.println(m.payment_type_id);
            System.out.println(m.secure_thumbnail);
            System.out.println(m.status);
            System.out.println("-------------------------");

        }
    }
}
